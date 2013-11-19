package eu.first.sentify.widget.drilldown.app;

import javax.xml.datatype.XMLGregorianCalendar;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

import eu.first.sentify.configuration.Functions;
import eu.first.sentify.widget.drilldown.DDownListNuevo;



/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class DrillDownApp extends UI
{

    @Override
    protected void init(VaadinRequest request) {


		try {
			//String sentimentObjectURI = "";
			String documentId = request.getParameter("docId");
		    if (documentId == null) {
		    	System.out.println("No parameter given");
		    	//sentimentObjectURI = "";
		    } else {
		    	System.out.println("parameter " + documentId);
		    	//sentimentObjectURI = "http://project-first.eu/ontology#" + companyName;
		    }

			DDownListNuevo soL = new DDownListNuevo(Long.decode(documentId), null);

			setContent(soL);

		} catch (Exception e) {
			System.out.println(e);
		}
    }

}
