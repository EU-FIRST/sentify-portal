package eu.first.sentify.companypage;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.CustomizedSystemMessages;
import com.vaadin.server.SystemMessages;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.UI;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
@Theme("mytheme")
@Title("FIRST")
public class MyVaadinUI extends UI {

	
	@Override
	protected void init(VaadinRequest request) {

		// Create custom layout from "index.html" template.
		CustomLayout custom = new CustomLayout("index");
		custom.addStyleName("styles");
		custom.setSizeFull();

		setContent(custom);
			
	}
	
	public static SystemMessages getSystemMessages() {
	    CustomizedSystemMessages messages =
	            new CustomizedSystemMessages();

	    // remove "connection problem" message & avoid showing it all the time when application is disconnected 
	    // and refresher is trying to fetch the data
	    
	    //messages.setCommunicationErrorCaption("Connection problem");
	    //messages.setCommunicationErrorMessage("<img src=\"images/socket.png\"> The connection with server has been lost. Please save any unsaved data.");
	    messages.setCommunicationErrorNotificationEnabled(false);

	    return messages;
	}

}
