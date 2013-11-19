package eu.first.sentify.widgets.documentsanalyzed;

import java.io.Serializable;
import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import eu.first.commons.ws.client.SentimentServiceStub;
import eu.first.sentify.configuration.Functions;



public class DocumentsAnalysedCounter {
	
	double documentCount = 0;
	//final CounterThread thread;
	Functions f = new Functions();
	
	private DocumentsAnalysedCounter() { 
		//thread = new CounterThread();
		//thread.start();
		refreshDocumentCounter();
	}
	
    /**
    * SingletonHolder is loaded on the first execution of Singleton.getInstance() 
    * or the first access to SingletonHolder.INSTANCE, not before.
    */
    private static class SingletonHolder { 
            public static final DocumentsAnalysedCounter INSTANCE = new DocumentsAnalysedCounter();
    }

    public static DocumentsAnalysedCounter getInstance() {
            return SingletonHolder.INSTANCE;
    }

	
	private void refreshDocumentCounter() {
		
		try {
			SentimentServiceStub wp5 = new SentimentServiceStub(f.getWSDL());
			SentimentServiceStub.GetDocumentCount req = new SentimentServiceStub.GetDocumentCount();
			SentimentServiceStub.GetDocumentCountResponse response = wp5.getDocumentCount(req);
			//System.out.println(response.get_return());
			documentCount = response.get_return();
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//this.documentCount += 127;
	}
	
	public double getDocumentCount() {
		synchronized (DocumentsAnalysedCounter.this) {
			return documentCount;
		}
	}
	
	private class CounterThread extends Thread implements Serializable {
		private static final long serialVersionUID = 6969871601928939400L;

		private boolean active = true;
		
		public CounterThread() {
			
		}

		public void stopThread() {
			this.active=false;
		}
		
		@Override
		public void run() {

			try {
				while (active == true) {
					sleep(15000);
					
					// synchronize with the application, to avoid concurrent modifications
					// (anyway it's not crucial)
					synchronized (DocumentsAnalysedCounter.this) {
						refreshDocumentCounter();
					}
					
				}
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}


