package eu.first.sentify.widget.companynewslist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.JButton;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;

import eu.first.sentify.helpers.TextEncodingFix;

public class ButtonRow extends CustomComponent {
	

	public ButtonRow(Button bN, Button bP) {
		//fixtext is a temporary fix for UTF-8 encoding glitches in the pipeline
		//Link title = new Link(TextEncodingFix.fixText(doc.getTitle()), new ExternalResource(doc.getInternalLink()));
		
		CustomLayout custom = new CustomLayout("buttonBoth");
		
		custom.addComponent(bP, "buttonP");
		custom.addComponent(bN, "buttonN");
		
		//custom.addStyleName("styles");
		custom.setSizeFull();		
		
		setSizeUndefined();
		setCompositionRoot(custom);
	}
	
}
