package eu.first.sentify.widget.companynewslistfuzzy;

import java.text.SimpleDateFormat;
import java.util.Locale;

import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;

import eu.first.sentify.helpers.TextEncodingFix;

public class SingleRow extends CustomComponent {

	public SingleRow(SentimentDocFuzzy doc) {
		//this should never, ever happen, but...
		if (doc.getTitle() == null) {
			doc.setTitle("Untitled");
		}
		//TextEncodingFix.fixText is a temporary fix for UTF-8 encoding glitches in the pipeline (manual correction of wrongly encoded characters)
		
		Link title = new Link(TextEncodingFix.fixText(doc.getTitle()), new ExternalResource(doc.getInternalLink()));
		
		CustomLayout customPositive = null;
		CustomLayout customNegative = null;
		CustomLayout custom = null;
		
		//System.out.println(doc.getFuzzyPositive() + " " + doc.getFuzzyNegative());
		
		//PRUEBA FUZZY
		switch (doc.getFuzzyPositive()) {
		        case 1:  customPositive = new CustomLayout("rowPositive1");
		                 break;
		        case 2:  customPositive = new CustomLayout("rowPositive2");
		                 break;
		        case 3:  customPositive = new CustomLayout("rowPositive3");
		                 break;
		        case 4:  customPositive = new CustomLayout("rowPositive4");
		                 break;
		        case 5:  customPositive = new CustomLayout("rowPositive5");
		                 break;
		        //special case: sentiment not available
		        case 6:  customPositive = new CustomLayout("rowPositive0");
                		 break;		     
                default:
                		break;
		}  
				
		switch (doc.getFuzzyNegative()) {
		        case 1:  customNegative = new CustomLayout("rowNegative1");
		        		 break;
		        case 2:  customNegative = new CustomLayout("rowNegative2");
		        		 break;
		        case 3:  customNegative = new CustomLayout("rowNegative3");
		        		 break;
		        case 4:  customNegative = new CustomLayout("rowNegative4");
		        		 break;
		        case 5:  customNegative = new CustomLayout("rowNegative5");
		        		 break;
		        //special case: sentiment not available
		        case 6:  customNegative = new CustomLayout("rowNegative0");
		        		 break;		     
                default:
            		break;
		}
		
		if (doc.score >= 0) {
			//ROWUP CASE			
			custom = new CustomLayout("rowFuzzyUp");
		}else{
			//ROWDOWN CASE
			custom = new CustomLayout("rowFuzzyDown");
		}
			
		
		//custom.addStyleName("styles");
		custom.setSizeFull();

		custom.addComponent(title, "title");
		custom.addComponent(customPositive, "positiveStars");
		custom.addComponent(customNegative, "negativeStars");
		
		String source = doc.getSource();
		Link sourceLink; 
		
		if (source == null) {
			source = "No source provided";			
			Label lbl = new Label(source);
			lbl.addStyleName("inline");
			custom.addComponent(lbl, "source");
		} else {
			sourceLink = new Link(source, new ExternalResource(doc.getUrl()));
			sourceLink.addStyleName("inline");		
			custom.addComponent(sourceLink, "source");
		}
		
		String dateString = new SimpleDateFormat("EEE, MMM d, yyyy", Locale.ENGLISH).format(doc.getDate());	
		Label lbl = new Label(dateString);
		lbl.addStyleName("inline");
		custom.addComponent(lbl, "date");

		setSizeUndefined();
		setCompositionRoot(custom);
	}
	
	public SingleRow(Label doc) {
		CustomLayout custom = null;

		custom = new CustomLayout("rowNormal");

		//custom.addStyleName("styles");
		custom.setSizeFull();

		custom.addComponent(doc, "doc1");

		setSizeUndefined();
		setCompositionRoot(custom);
	}
	
}
