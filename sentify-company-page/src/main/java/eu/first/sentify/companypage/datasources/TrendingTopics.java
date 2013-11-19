package eu.first.sentify.companypage.datasources;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.first.commons.ws.client.SentimentServiceParseExceptionException0;
import eu.first.commons.ws.client.SentimentServiceStub;
import eu.first.commons.ws.client.SentimentServiceStub.GetTrendingTopicsResponse;
import eu.first.commons.ws.client.SentimentServiceStub.GetTrendingTopics;
import eu.first.commons.ws.client.SentimentServiceStub.GetSentimentIndexScoreObjectLastNDays;
import eu.first.commons.ws.client.SentimentServiceStub.ScoreByDate;
import eu.first.commons.ws.client.SentimentServiceStub.TrendingTopic;
import eu.first.sentify.configuration.Functions;
import eu.first.sentify.searchsentimentobjects.CompanySearch;

public class TrendingTopics extends HttpServlet {
	
	Functions f = new Functions();
	CompanySearch cs = CompanySearch.getInstance();
	TimeSeriesCache tsCache = new TimeSeriesCache();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();

		if (/* request.getParameter("startdate") != null && */ request.getParameter("enddate") != null) {
			if (/* request.getParameter("startdate").isEmpty() == false && */ request.getParameter("enddate").isEmpty() == false) {
				
				String ts = tsCache.getTopics(request.getParameter("enddate"));
				if (ts != null) {
			    	out.println(ts);
					System.out.println("From cache.");
				} else {
					try {
						StringBuilder sb = new StringBuilder();
						
						//get date -30 days
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
				        Calendar calEnd = Calendar.getInstance();
				        Date enddate;
						enddate = new SimpleDateFormat("yy-MM-dd", Locale.ENGLISH).parse(request.getParameter("enddate"));
				        calEnd.setTime(enddate);
				        calEnd.add(Calendar.DATE, -30);
				        String startdate = dateFormat.format(calEnd.getTime());
					
						SentimentServiceStub service = new SentimentServiceStub(f.getWSDL());
						GetTrendingTopics req = new GetTrendingTopics();
						req.setStartdate(startdate);
						req.setEnddate(request.getParameter("enddate"));
						req.setLimit(25);

						GetTrendingTopicsResponse serviceResponse;
						
						serviceResponse = service.getTrendingTopics(req);
				    	TrendingTopic[] topics = serviceResponse.get_return();

				    	if (topics != null) {
							
							sb.append("[");
					    	
					    	for (TrendingTopic tt : topics) {
					    		String topicUri = tt.getTopic().getOntologyConceptUri();
					    		String topicName = cs.getCompanyNameByURI(topicUri);
					    		if (topicName == null) {
					    			topicName = tt.getTopic().getName();
					    		}
					    		if (topicName.contains("org_WHO"))
					    			continue;
					    		Long occurences = tt.getOcurrences();
					    		Double score = tt.getScore();
					    		
					    		
					    		//System.out.println(s.getScore() + " " + s.getDate());	
					    		sb.append("[\"" + topicName + "\", " + occurences + "," + score +"],\n");
					    		
					    	}
					    	sb.deleteCharAt(sb.length()-2);
					    	sb.append("]");
					    	
					    	//store result in a raw form (without callback)
					    	tsCache.putTopics(request.getParameter("enddate"), sb.toString());
					    	
					    	out.println(sb.toString());
					    	
						} else {
							out.println("[]");
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SentimentServiceParseExceptionException0 e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				
				}

			}
		}
		
	}
	
}
