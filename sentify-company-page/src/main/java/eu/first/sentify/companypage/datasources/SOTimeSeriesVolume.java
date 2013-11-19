package eu.first.sentify.companypage.datasources;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.first.commons.ws.client.SentimentServiceParseExceptionException0;
import eu.first.commons.ws.client.SentimentServiceStub;
import eu.first.commons.ws.client.SentimentServiceStub.GetDocumentsbySentimentObject;
import eu.first.commons.ws.client.SentimentServiceStub.GetDocumentsbySentimentObjectResponse;
import eu.first.commons.ws.client.SentimentServiceStub.GetSentimentDocumentObject;
import eu.first.commons.ws.client.SentimentServiceStub.GetSentimentDocumentObjectResponse;
import eu.first.commons.ws.client.SentimentServiceStub.GetSentimentIndexScoreObjectLastNDays;
import eu.first.commons.ws.client.SentimentServiceStub.GetSentimentIndexScoreObjectLastNDaysResponse;
import eu.first.commons.ws.client.SentimentServiceStub.ScoreByDate;
import eu.first.commons.ws.client.SentimentServiceStub.ScoreByDocument;
import eu.first.commons.ws.client.SentimentServiceStub.Sentiment;
import eu.first.commons.ws.client.SentimentServiceStub.TrendingTopic;
import eu.first.sentify.configuration.Functions;
import eu.first.sentify.reputationtopics.ReputationTopicSearch;
import eu.first.sentify.reputationtopics.SentimentIndicatorSearch;



public class SOTimeSeriesVolume extends HttpServlet {
	
	Functions f = new Functions();
	TimeSeriesCache tsCache = new TimeSeriesCache();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String mimeType = getServletContext().getMimeType("test.js");
		response.setContentType(mimeType);
		PrintWriter out = response.getWriter();
		
		String sentimentObject;
		String startDate;
		String endDate;
		String topic;
		String indicator;
		
		if (request.getParameter("so") != null) {
			sentimentObject = request.getParameter("so");
		} else {
			sentimentObject = "";
		}
		
		//this parameter is crucial
		if (request.getParameter("enddate") != null) {
			endDate = request.getParameter("enddate");
		} else {
			endDate = "";
		}
		
		//start date if not present is computed to default value 
		if (request.getParameter("startdate") != null && request.getParameter("startdate").isEmpty() == false) {
			startDate = request.getParameter("startdate");
		} else {
			if (endDate.isEmpty() == false) {
				//get date -30 days
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        Calendar calEnd = Calendar.getInstance();
		        Date dEndDate;
				try {
					dEndDate = new SimpleDateFormat("yy-MM-dd", Locale.ENGLISH).parse(endDate);
					calEnd.setTime(dEndDate);
			        calEnd.add(Calendar.DATE, -30);
			        startDate = dateFormat.format(calEnd.getTime());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					startDate = "";
					e.printStackTrace();
				}
		        
				
			} else {
				startDate = "";
			}
		}
		
		if (request.getParameter("topic") != null) {
			ReputationTopicSearch rts = ReputationTopicSearch.getInstance();
			
			topic = request.getParameter("topic");
			if (rts.getReputationTopicURIByName(topic) == null) {
				topic = "";
			} else {
				topic = rts.getReputationTopicURIByName(topic);
			}
		} else {
			topic = "";
		}
		
		if (request.getParameter("indicator") != null) {
			SentimentIndicatorSearch rts = SentimentIndicatorSearch.getInstance();
			
			indicator = request.getParameter("indicator");
			if (rts.getSentimentIndicatorURIByShortName(indicator) == null) {
				indicator = "";
			} else {
				indicator = rts.getSentimentIndicatorURIByShortName(indicator);
			}
		} else {
			indicator = "";
		}

		if (sentimentObject.isEmpty() == false && startDate.isEmpty() == false && endDate.isEmpty() == false) {
			//check callback parameter
			String callback = null;
			if (request.getParameter("callback") != null && request.getParameter("callback").isEmpty() == false) {
				callback = request.getParameter("callback");
			}
			
			String ts = tsCache.getTimeSeries(sentimentObject, startDate, endDate, indicator, topic);
			if (ts != null) {
				//add callback function (JSON-P)
		    	if (callback != null) {
		    		out.println(callback + "(" + ts + ");");
		    	} else {
		    		out.println(ts);
		    	}
				System.out.println("From cache.");
			} else {
				
				try {
					StringBuilder sb = new StringBuilder();
										
					
			        
					SentimentServiceStub service = new SentimentServiceStub(f.getWSDL());
					//GetSentimentDocumentObject req = new GetSentimentDocumentObject();
					GetDocumentsbySentimentObject req = new GetDocumentsbySentimentObject();
					//GetSentimentIndexScoreObjectLastNDays req = new GetSentimentIndexScoreObjectLastNDays();
					req.setSentiment_object_uri("http://project-first.eu/ontology#" + request.getParameter("so"));
					if (topic.isEmpty() != true) {
						req.setSentiment_subfeature_uri(topic);	
					}
					if (indicator.isEmpty() != true) {
						req.setSentiment_feature_uri(indicator);	
					}
					req.setEnddate(endDate);
					req.setStartdate(startDate);
					req.setLimit(-1);
					req.setOffset(-1);
					System.out.println("Getting startdate: " + startDate + " enddate: " + endDate + " so: " + request.getParameter("so") + " topic: " + topic);
			    	//GetSentimentDocumentObjectResponse serviceResponse;
					
					GetDocumentsbySentimentObjectResponse serviceResponse;
					serviceResponse = service.getDocumentsbySentimentObject(req);
			    	ScoreByDocument[] sentiments = serviceResponse.get_return();
    	
			    	//System.out.println(sentiment.length);
			    	
			    	if (sentiments != null) {
			    		sb.append("[");
				    	
				    	int pos = 0;
				    	int neg = 0;
				    	HashMap<Long, Integer> hm = new HashMap<Long, Integer>();
				    	
			    		for (ScoreByDocument s : sentiments) {
				    		if (s.getDocument() != null) {
				    			Long date = s.getDocument().getPublicationDate().getTime();
					    		//System.out.println(date);
					    		if (hm.containsKey(date)) {
					    			Integer val = hm.get(date) + 1;
					    			hm.put(date, val);
					    		} else {
					    			hm.put(date, 1);
					    		}				    			
				    		}
				    		//System.out.println(s.getScore() + " " + s.getDate());	
				    		//sb.append("[" + s.getDate().getTime() + ", " + s.getScore() + "],\n");
				    	}
				    	int count = 0;
				    	SortedSet<Long> keys = new TreeSet<Long>(hm.keySet());
				    	for (Long l : keys) {
				    		//System.out.println(l + "    " + hm.get(l));
				    		sb.append("[" + l + ", " + hm.get(l) + "],\n");
				    		count += hm.get(l);
				    	}
				    	//System.out.println(count);
				    	sb.deleteCharAt(sb.length()-2);
				    	sb.append("]");
				    	
				    	//store only raw data in cache (without callback)
				    	tsCache.putTimeSeries(sentimentObject, startDate, endDate, indicator, topic, sb.toString());
				    	
				    	//add callback function (JSON-P)
				    	if (callback != null) {
				    		sb.insert(0, callback + "(");
				    		sb.append(");");
				    	}
				    	
				    	out.println(sb.toString());
				    	
			    	} else {
			    		out.println("[]");
			    	}
			    	
				} catch (SentimentServiceParseExceptionException0 e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					out.println("[error: 'error']");
				} 
			}
			
			
			
		}
		

		
	}
	
}
