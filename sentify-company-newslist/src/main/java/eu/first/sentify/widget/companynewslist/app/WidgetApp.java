package eu.first.sentify.widget.companynewslist.app;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.github.wolfie.refresher.Refresher;
import com.github.wolfie.refresher.Refresher.RefreshListener;
import com.vaadin.annotations.Theme;
import com.vaadin.server.CustomizedSystemMessages;
import com.vaadin.server.SystemMessages;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

import eu.first.sentify.configuration.Functions;
import eu.first.sentify.reputationtopics.ReputationTopicSearch;
import eu.first.sentify.reputationtopics.SentimentIndicatorSearch;
import eu.first.sentify.searchsentimentobjects.CompanySearch;
import eu.first.sentify.widget.companynewslist.DocumentList;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
@Theme("mytheme")
public class WidgetApp extends UI {
	
	DocumentList dl;
	
	@Override
	protected void init(VaadinRequest request) {
//		final Refresher refresher = new Refresher();
		
		try {
			
			//TODO refactor this code & generelize to Sentiment Object
			String sentimentObjectURI = "";
			String soParam = request.getParameter("so");
			if (soParam == null) {
				System.out.println("No parameter given, trying search query parameter...");
		    	if (request.getParameter("q") != null) {
			    	String searchQueryParam = request.getParameter("q");
			    	CompanySearch css = CompanySearch.getInstance();
			    	sentimentObjectURI = css.searchCompany(searchQueryParam).getURI();		    	
		    	} 
			} else {
				System.out.println("parameter " + soParam);
		    	sentimentObjectURI = "http://project-first.eu/ontology#" + soParam;
			}
			
			
//			String companyName = request.getParameter("company");
//		    if (companyName == null) {
//		    	System.out.println("No parameter given, trying search query parameter...");
//		    	if (request.getParameter("q") != null) {
//			    	String searchQueryParam = request.getParameter("q");
//			    	CompanySearch css = CompanySearch.getInstance();
//			    	sentimentObjectURI = css.searchCompany(searchQueryParam).getURI();		    	
//		    		
//		    	} else {
//		    		//try country 
//		    		if (request.getParameter("country") != null) {
//		    			//String searchQueryParam = request.getParameter("country");
//				    	//CompanySearch css = CompanySearch.getInstance();
//		    			companyName = request.getParameter("country");
//				    	sentimentObjectURI = "http://project-first.eu/ontology#" + companyName;	
//		    		}
//		    	}
//		    	
//		    } else {
//		    	System.out.println("parameter " + companyName);
//		    	sentimentObjectURI = "http://project-first.eu/ontology#" + companyName;
//		    }

			Functions f = new Functions();
			ReputationTopicSearch rts = ReputationTopicSearch.getInstance();
			
			//check for reputation topic in URI
            String reputationTopicString = request.getParameter("topic");
            String reputationTopicURI = null;
            if ((reputationTopicString != null) && (reputationTopicString.isEmpty() == false)) {
            	reputationTopicURI = rts.getReputationTopicURIByShortName(reputationTopicString);
            	System.out.println("Reputation topic: " + reputationTopicURI);
            }

			SentimentIndicatorSearch sis = SentimentIndicatorSearch.getInstance();

            //check for sentiment indicator in URI
            String sentimentIndicatorString = request.getParameter("sentiment");
            String sentimentIndicatorURI = null;
            if ((sentimentIndicatorString != null) && (sentimentIndicatorString.isEmpty() == false)) {
            	sentimentIndicatorURI = sis.getSentimentIndicatorURIByShortName(sentimentIndicatorString);
            	System.out.println("Sentiment Indicator: " + sentimentIndicatorURI);
            }
            
			//By default (there's no expressed date), startdate is 7 days ago and enddate is today
			XMLGregorianCalendar today = f.getXMLGregorianCalendarNow();
			XMLGregorianCalendar oneWeekAgo = f.toXmlGregorianCalendar(today
					.toGregorianCalendar().getTimeInMillis() - f.get7Days());

            String fromDateString = request.getParameter("from-date");
            String toDateString = request.getParameter("to-date");
			System.out.println("Newslist - Reading params: URI: " + sentimentObjectURI + " from: " + fromDateString + " to: " + toDateString + " indicator: "+sentimentIndicatorURI+" topic: " + reputationTopicString);

            //if user define date range, use it. If not, use defaults			
            if ((fromDateString != null) && (fromDateString.isEmpty() == false) && (toDateString != null) && (toDateString.isEmpty() == false)) {
            	try {
            		XMLGregorianCalendar fromDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(fromDateString);
            		XMLGregorianCalendar toDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(toDateString);
            		System.out.println("from date: " + fromDateString + " to date: " + toDateString + " topic: " + reputationTopicURI);
            		dl = new DocumentList(this, toDate, fromDate, sentimentObjectURI, sentimentIndicatorURI, reputationTopicURI);
            	} catch (Exception e) {
            		System.out.println("Date parsing error. Reset to default (7 days)");
    				dl = new DocumentList(this, today, oneWeekAgo, sentimentObjectURI, sentimentIndicatorURI, reputationTopicURI);
            	}
            	
            	
            	
            } else {	
            	//in case we have only "end date" defined, use it.
            	//TODO: define range if only end date is present.

            	System.out.println("ALP=> Date is not defined");
            	
            	//hack to show only latest news
            	String latest = request.getParameter("latest");
            	if (latest != null && latest.isEmpty() == false) {
            		//use only today news
    				dl = new DocumentList(this, today, today, sentimentObjectURI, sentimentIndicatorURI, reputationTopicURI);
            		
            	} else {
            		//use the whole week
    				dl = new DocumentList(this, today, oneWeekAgo, sentimentObjectURI, sentimentIndicatorURI, reputationTopicURI);           		
            	}
            }
            

//			addExtension(refresher);
//			refresher.addListener(new DocumentListUpdateListener());
			setContent(dl);

//			refresher.setRefreshInterval(5000);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static SystemMessages getSystemMessages() {
	    CustomizedSystemMessages messages =
	            new CustomizedSystemMessages();

	    messages.setCommunicationErrorCaption("Connection problem");
	    messages.setCommunicationErrorMessage("<img src=\"images/socket.png\"> The connection with server has been lost. Please save any unsaved data.");
	    messages.setCommunicationErrorNotificationEnabled(false);

	    return messages;
	}

    public class DocumentListUpdateListener implements RefreshListener {
        
        @Override
        public void refresh(final Refresher source) {
            // stop polling
            //removeExtension(source);
            dl.displayDocumentList();           
        	//analyzed.setValue(String.format("%1$,.0f", dac.getDocumentCount()));

        }
    }
}
