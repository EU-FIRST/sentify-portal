package eu.first.sentify.widget.drilldown;

import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.ProgressIndicator;


public class RowList extends CustomComponent{
	public RowList(Label doc) {
		CustomLayout custom = null;

		custom = new CustomLayout("rowEmptyDD");

		custom.addStyleName("styles");
		custom.setSizeFull();


		custom.addComponent(doc, "doc1");

		setSizeUndefined();
		setCompositionRoot(custom);
	}
	
    public RowList(Button soName, boolean up, boolean hasPh) {
    	CustomLayout custom = null;
    	
    	if(hasPh){
    		if(up){
    			custom = new CustomLayout("rowUpDD");
    		}else{
    			custom = new CustomLayout("rowDownDD");
    		}
    		custom.addStyleName("styles");
    		custom.setSizeFull();

		
    		custom.addComponent(soName, "doc1");
    	
    		setSizeUndefined();
    		setCompositionRoot(custom);
    		
    	} else{
    		
    		//This case should never happen
    		custom = new CustomLayout("rowEmptyDD");
    		custom.addStyleName("styles");
    		custom.setSizeFull();
    		
    		custom.addComponent(soName, "doc1");
        	
    		setSizeUndefined();
    		setCompositionRoot(custom);
    	}
    }	
}