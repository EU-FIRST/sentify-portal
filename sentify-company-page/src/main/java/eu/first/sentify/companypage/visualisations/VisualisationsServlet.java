package eu.first.sentify.companypage.visualisations;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VisualisationsServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if (request.getParameter("vis") != null) {
			
			String query =  request.getParameter("vis");
			
			Frame frame = new Frame();
			
			if (query.equalsIgnoreCase("timeline")) {
				frame.setUrl("http://first.ijs.si/demos/SentimentTimelineDemo/?stock=AAPL");
			}
			
			if (query.equalsIgnoreCase("enriched")) {
				frame.setUrl("http://first.ijs.si/TwitterSentiVisService/DisplayTweets.aspx?date=2012-11-09&stock=$GOOG&css=http://first.ijs.si/demos/common/css/tweetsTable.css");
			}
			
			if (query.equalsIgnoreCase("cooccurrence")) {
				frame.setUrl("http://first-vm4.ijs.si/occurrence/");
			}
			
			if (query.equalsIgnoreCase("pumpanddump")) {
				frame.setUrl("http://first.ijs.si/demos/PumpAndDumpDemo/");
			}
			
			if (query.equalsIgnoreCase("redpin")) {
				frame.setUrl("soon.jsp");
			}
			
			if (query.equalsIgnoreCase("canyon")) {
				frame.setUrl("soon.jsp");
			}
			
			request.setAttribute("frame", frame);

		}
		
		RequestDispatcher dispatcher =
			      request.getRequestDispatcher("visualisations.jsp");
		dispatcher.forward(request, response);		
		
	}
	
}
