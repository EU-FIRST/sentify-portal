package eu.first.sentify.widget.companynewslist;

import java.text.SimpleDateFormat;
import java.util.Locale;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;

import eu.first.sentify.helpers.TextEncodingFix;

public class SingleRow extends CustomComponent {

	public SingleRow(SentimentDoc doc) {
		//fixtext is a temporary fix for UTF-8 encoding glitches in the pipeline
		Link title = new Link(TextEncodingFix.fixText(doc.getTitle()), new ExternalResource(doc.getInternalLink()));
		
		CustomLayout custom = null;

		if (doc.score >= 0) {
			custom = new CustomLayout("rowUp");
		} else {
			custom = new CustomLayout("rowDown");
		}
		
		//custom.addStyleName("styles");
		custom.setSizeFull();

		custom.addComponent(title, "title");
		
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
