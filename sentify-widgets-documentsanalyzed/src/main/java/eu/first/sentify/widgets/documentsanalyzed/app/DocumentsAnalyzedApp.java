package eu.first.sentify.widgets.documentsanalyzed.app;

import java.text.DecimalFormat;

import com.github.wolfie.refresher.Refresher;
import com.github.wolfie.refresher.Refresher.RefreshListener;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;

import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

import eu.first.sentify.widgets.documentsanalyzed.DocumentsAnalysedCounter;

/**
 * The Application's "main" class
 */
public class DocumentsAnalyzedApp extends UI
{
	private static final long serialVersionUID = 3597324160734558770L;

	DocumentsAnalysedCounter dac;
	Label analyzed;
	
	//double mockUpValue = 3058631;
	//boolean mockup = true; //for debugging purpose (to avoid expensive queries)
	
    @Override
    protected void init(VaadinRequest request) {
    	
    	UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
    	    @Override
    	    public void error(com.vaadin.server.ErrorEvent event) {
    	        // Find the final cause
    	        String cause = "<b>The click failed because:</b><br/>";
    	        for (Throwable t = event.getThrowable(); t != null;
    	             t = t.getCause())
    	            if (t.getCause() == null) // We're at final cause
    	                cause += t.getClass().getName() + "<br/>";
    	        
    	        // Display the error message in a custom fashion
    	        setContent(new Label(cause, ContentMode.HTML));
    	        System.out.println("Error occured!");
    	        // Do the default error handling (optional)
    	        doDefault(event);
    	    } 
    	});
    	
    	
    	
    	
    	//final Refresher refresher = new Refresher();
    	dac = DocumentsAnalysedCounter.getInstance();
    	
    	
        try {
       	
			analyzed = new Label(String.format("%1$,.0f", dac.getDocumentCount() ));
	        
			//addExtension(refresher);
			
	        //addComponent(analyzed);
			setContent(analyzed);
	        //refresher.addListener(new DocumentsAnalyzedListener());
	        
	        
	        //refresher.setRefreshInterval(5000);
	        
        } catch (Exception e) {
        	System.out.println(e);
        }
        
    }
    
    
    public class DocumentsAnalyzedListener implements RefreshListener {
        private static final long serialVersionUID = -8765221895426102605L;
        
        @Override
        public void refresh(final Refresher source) {
            // stop polling
            //removeExtension(source);
                        
        	analyzed.setValue(String.format("%1$,.0f", dac.getDocumentCount()));

        }
    }

}
