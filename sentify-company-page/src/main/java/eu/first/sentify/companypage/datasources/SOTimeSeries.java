package eu.first.sentify.companypage.datasources;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import eu.first.commons.ws.client.SentimentServiceParseExceptionException0;
import eu.first.commons.ws.client.SentimentServiceStub;
import eu.first.commons.ws.client.SentimentServiceStub.GetSentimentIndexScoreObject;
import eu.first.commons.ws.client.SentimentServiceStub.GetSentimentIndexScoreObjectLastNDays;
import eu.first.commons.ws.client.SentimentServiceStub.GetSentimentIndexScoreObjectLastNDaysResponse;
import eu.first.commons.ws.client.SentimentServiceStub.GetSentimentIndexScoreObjectPerDay;
import eu.first.commons.ws.client.SentimentServiceStub.GetSentimentIndexScoreObjectPerDayResponse;
import eu.first.commons.ws.client.SentimentServiceStub.GetSentimentIndexScoreObjectResponse;
import eu.first.commons.ws.client.SentimentServiceStub.ScoreByDate;
import eu.first.commons.ws.client.SentimentServiceStub.TrendingTopic;
import eu.first.sentify.configuration.Functions;
import eu.first.sentify.reputationtopics.ReputationTopicSearch;
import eu.first.sentify.reputationtopics.SentimentIndicatorSearch;



public class SOTimeSeries extends HttpServlet {
	
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
		
		if (request.getParameter("startdate") != null) {
			startDate = request.getParameter("startdate");
		} else {
			startDate = "";
		}
		
		if (request.getParameter("enddate") != null) {
			endDate = request.getParameter("enddate");
		} else {
			endDate = "";
		}
		
		if (request.getParameter("topic") != null) {
			ReputationTopicSearch rts = ReputationTopicSearch.getInstance();
			
			topic = request.getParameter("topic");
			if (rts.getReputationTopicURIByShortName(topic) == null) {
				topic = "";
			} else {
				topic = rts.getReputationTopicURIByShortName(topic);
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

		if (sentimentObject.isEmpty() == false /* && startDate.isEmpty() == false */ && endDate.isEmpty() == false) {
			System.out.println("Timeseries: so: startdate enddate topic: " + topic);
			
			//callback parameter for JSON-P
			String callback = null;
			if (request.getParameter("callback") != null && request.getParameter("callback").isEmpty() == false) {
				callback = request.getParameter("callback");
			}
			
			String ts = tsCache.getTimeSeries(sentimentObject, startDate, endDate, indicator, topic);
			
			if (ts != null) {
				if (callback != null) {
		    		out.println(callback + "(" + ts + ");");
		    	} else {
		    		out.println(ts);
		    	}
				System.out.println("From cache.");
			} else {
				
				try {
					StringBuilder sb = new StringBuilder();
					
					//moving average (X days)
					int movingAverage = 3;
					
					ScoreByDate[] score = null;
					SentimentServiceStub service = new SentimentServiceStub(f.getWSDL());
					
					if (startDate.isEmpty() == true) {
						
						GetSentimentIndexScoreObjectLastNDays req = new GetSentimentIndexScoreObjectLastNDays();
						req.setSentiment_object_uri("http://project-first.eu/ontology#" + request.getParameter("so"));
						
						if (topic.isEmpty() != true) {
							req.setSentiment_subfeature_uri(topic);	
						}
						if (indicator.isEmpty() != true) {
							req.setSentiment_feature_uri(indicator);	
						}
						req.setEnddate(request.getParameter("enddate"));
						
						//the moving average takes out x-1 days from the time serie
						//for 5 days moving average we need X+4 days in the time series
						req.setDays(30 + movingAverage - 1);

				    	GetSentimentIndexScoreObjectLastNDaysResponse serviceResponse;
						
						serviceResponse = service.getSentimentIndexScoreObjectLastNDays(req);
						score = serviceResponse.get_return();
					} else {
						GetSentimentIndexScoreObjectPerDay req = new GetSentimentIndexScoreObjectPerDay();
						req.setSentiment_object_uri("http://project-first.eu/ontology#" + request.getParameter("so"));
						
						if (topic.isEmpty() != true) {
							req.setSentiment_subfeature_uri(topic);	
						}
						if (indicator.isEmpty() != true) {
							req.setSentiment_feature_uri(indicator);	
						}
						req.setEnddate(request.getParameter("enddate"));
						String newStartDate = getXDaysBefore(startDate, movingAverage - 1);
						req.setStartdate(newStartDate);
						
						//the moving average takes out x-1 days from the time serie
						//for 5 days moving average we need X+4 days in the time series
						//req.setDays(30 + movingAverage - 1);

				    	GetSentimentIndexScoreObjectPerDayResponse serviceResponse;
						
						serviceResponse = service.getSentimentIndexScoreObjectPerDay(req);
						score = serviceResponse.get_return();
					}
					
					
					score = getMovingAverageXDays(score, movingAverage);
			    	
					if (score != null) {
						
						sb.append("[");
				    	
				    	for (ScoreByDate s : score) {
				    		//System.out.println(s.getScore() + " " + s.getDate());	
				    		sb.append("[" + s.getDate().getTime() + ", " + s.getScore() + "],\n");
				    	}
				    	sb.deleteCharAt(sb.length()-2);
				    	sb.append("]");
				    	
				    	//store result in a raw form (without callback)
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
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		
		
	}
	
	/**
	 * 
	 * Calculate moving average (from last X days) of the given time series array (ScoreByDate)
	 * note that this will shrink the array by X-1 oldest records. 
	 * We assume oldest-first order. 
	 * 
	 * @param score 	Time series array (of object ScoreByDate). 
	 * @param days 		Number of days for average function.
	 * @return 			New array with smoothen time series.
	 */
	public ScoreByDate[] getMovingAverageXDays(ScoreByDate[] score, int days) {
		ArrayList<ScoreByDate> avg = new ArrayList<ScoreByDate>();
		
		if (score.length >= days) {
			for (int i=days-1; i < score.length ; i++) {
				double sum = 0;
				for (int j=0; j<days; j++) {
					sum += score[i-j].getScore();
				}
				ScoreByDate sbd = new ScoreByDate();
				sbd.setDate(score[i].getDate());
				sbd.setScore(sum/days);
				avg.add(sbd);
				
			}
		}
		return avg.toArray(new ScoreByDate[avg.size()]);
		
//		ArrayList<Double> avg = new ArrayList<Double>();
//		avg.add((score[0].getScore()+score[2].getScore())/2);
//
//		if (score.length >= days) {
//			avg.add((score[0].getScore()+score[2].getScore())/2);
//			for (int i=1; i<score.length-1; i++) {
//				avg.add((score[i-1].getScore() + score[i].getScore() + score[i+1].getScore()) / 3);	
//			}
//			avg.add((score[score.length-2].getScore()+score[score.length-1].getScore())/2);
//			
//			for (int i=0; i<score.length; i++) {
//				score[i].setScore(avg.get(i));
//			}
//		} 
//
//		return score;
	}
	
	
	private int daysBetween(String  d1, String d2) throws ParseException{
		Calendar cal1 = new GregorianCalendar();
		Calendar cal2 = new GregorianCalendar();
		
		
		cal1.setTime(new SimpleDateFormat("yy-MM-dd", Locale.ENGLISH).parse(d1));
		cal2.setTime(new SimpleDateFormat("yy-MM-dd", Locale.ENGLISH).parse(d2));
		
		//System.out.println("Days= "+daysBetween(cal1.getTime(),cal2.getTime()));
		 
        return (int)( (cal1.getTime().getTime() - cal1.getTime().getTime()) / (1000 * 60 * 60 * 24));
	}
	
	private String getXDaysBefore(String date, int days) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calEnd = Calendar.getInstance();
        Date dEndDate = new SimpleDateFormat("yy-MM-dd", Locale.ENGLISH).parse(date);
		calEnd.setTime(dEndDate);
        calEnd.add(Calendar.DATE, -days);
        String returnDate = dateFormat.format(calEnd.getTime());
		return returnDate;
	}

	
}
