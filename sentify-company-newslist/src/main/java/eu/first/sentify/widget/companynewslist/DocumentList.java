package eu.first.sentify.widget.companynewslist;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.axis2.AxisFault;


import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.CustomLayout;

import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import eu.first.commons.ws.client.SentimentServiceParseExceptionException0;
import eu.first.commons.ws.client.SentimentServiceStub;
import eu.first.commons.ws.client.SentimentServiceStub.DocumentMetaData;
import eu.first.sentify.configuration.Functions;

public class DocumentList extends CustomComponent {

	// private static final int SLEEP_TIME_IN_MILLIS = 1000 * 60 * 2; // 2
	// minutes
	CustomLayout custom = null;
	VerticalLayout vo = null;
	String URI = "";
	Link doc = null;
	SingleRow errorRow = null;
	boolean error = false;
	boolean noDocs = false;
	// SingleRow rl = null;
	Functions f = new Functions();
	Logger logger = Logger.getLogger(DocumentList.class.getCanonicalName());
	int offSet = 0;
	int offSetToShow = 0;
	int numTotalDocs = 0;
	int numPags = 0;
	ButtonRow br = null;	
	Button bN = null;
	Button bP = null;
	PopupDateField date = null;
	String dateValue = "";
	XMLGregorianCalendar day = null;
	XMLGregorianCalendar today = null;
	String reputationTopic = null;
	String indicator = null;
	
	LinkedList<SentimentDoc> docList = new LinkedList<SentimentDoc>();
	HashMap<String, SentimentDoc> hashList = new HashMap<String, SentimentDoc>();
	
	public DocumentList(UI app, XMLGregorianCalendar today, XMLGregorianCalendar day, String URI, String indicator, String reputationTopicURI) {
		System.out.println("Documentlist created with URI: " + URI);
		logger.info("Documentlist created with URI: " + URI);
		
		this.URI = URI;
		this.day=day;
		this.today=today;
		this.reputationTopic = reputationTopicURI;
		this.indicator = indicator;
		
		// Create table template
		custom = new CustomLayout("newstable");
		// custom.addStyleName("styles");
		custom.setSizeFull();

		vo = new VerticalLayout();
		vo.setMargin(false);
		custom.addComponent(vo, "newslist");
		
		// Paint the document list table
		displayDocumentList();
		
		//PRUEBA BUTTON
		if((URI != null)&&!(URI.equals(""))){
			
		  if(!(this.error)){ 
			if(!(this.noDocs)){	
		  
				calculaMaxPags();

				if(numPags > 1){

					//Build buttons
					//int pTShow=offSet+1;
					int pTShow=offSetToShow+1;				
					if(pTShow > numPags) 
						pTShow = numPags;
					bN = new Button("Next >> ("+pTShow+" of "+numPags+")");				
					bN.setEnabled(true);
					bN.addClickListener(new Button.ClickListener() {
						public void buttonClick(ClickEvent event) {
							//offSet++;				        
							offSet=offSet+f.getMaxNews();
							offSetToShow++;
							bN.setEnabled(false);
							int pTShow=offSetToShow+1;						
							if(pTShow > numPags) 
								pTShow = numPags;						
							bN.setCaption("Next >> ("+pTShow+" of "+numPags+")"); 
							if(!bP.isEnabled())
								bP.setEnabled(true);
							//if(offSet < numPags){
							if(offSetToShow < numPags){
								displayDocumentList();
								vo.addComponent(br);
								bN.setEnabled(true);
							}else{
								bN.setEnabled(false);							
							}
							//bN.setCaption("Next >> ("+pTShow+" of "+numPags+")"); //PRUEBA
						} 
					});

					bP = new Button("<< Previous");
					//bP.setEnabled(true);
					bP.setVisible(true);
					bP.addClickListener(new Button.ClickListener() {
						public void buttonClick(ClickEvent event) { 
							bP.setEnabled(false);						
							//bP.setCaption("WAIT!"); //PRUEBA
							if(!bN.isEnabled())
								bN.setEnabled(true);
							//if(offSet>0){														
							if(offSetToShow>0){
								//bN.setCaption("Next >> ("+offSet+" of "+numPags+")");
								bN.setCaption("Next >> ("+offSetToShow+" of "+numPags+")");
								//offSet--;
								offSetToShow--;
								offSet=offSet-f.getMaxNews();
								displayDocumentList();
								vo.addComponent(br);
								bP.setEnabled(true);
							}else{
								bP.setEnabled(false);							
							}
							//bP.setCaption("<< Previous"); //PRUEBA
						} 
					});

					br = new ButtonRow(bN,bP);
					vo.addComponent(br);				
				}
			}
		  }
		}
		//FIN PRUEBA BUTTON
		
		// The composition root MUST be set
		setCompositionRoot(custom);
	  
	}

	public synchronized void displayDocumentList() {
		DocumentMetaData docu = null;
		
		//PRUEBA NEXT
		vo.removeAllComponents();
		
		//get today's date:
		/*Calendar currentDate = Calendar.getInstance(); 
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd"); 
		String dateNow = formatter.format(currentDate.getTime());
		
		currentDate.add(Calendar.DATE, -7);
		String weekAgo = formatter.format(currentDate.getTime());*/
				
		try {
			
			System.out.println("ALP=>Startdate= "+day.toString().substring(0,f.getLength()));
			logger.info("ALP=>Startdate= "+day.toString().substring(0,f.getLength()));

			System.out.println("ALP=>Enddate= "+today.toString().substring(0,f.getLength()));
			logger.info("ALP=>Enddate= "+today.toString().substring(0,f.getLength()));

			SentimentServiceStub wp5 = new SentimentServiceStub(f.getWSDL());

			SentimentServiceStub.GetDocumentsbySentimentObject req = new SentimentServiceStub.GetDocumentsbySentimentObject();
			req.setSentiment_object_uri(URI);
			req.setStartdate(day.toString().substring(0,f.getLength())); //weekAgo
			req.setEnddate(today.toString().substring(0,f.getLength())); //dateNow
			req.setLimit(f.getMaxNews());
			req.setOffset(offSet); //0);
			if (this.reputationTopic != null) {
				logger.info("Newslist w/ reputation topic: " + this.reputationTopic);
				req.setSentiment_subfeature_uri(this.reputationTopic);
			}
			if (this.indicator != null) {
				logger.info("Newslist w/ indicator: " + this.indicator);
				req.setSentiment_feature_uri(this.indicator);
			}
			
			System.out.println("ALP=>Inicio valores para getSentimentDocumentObject ");
			logger.info("ALP=>Inicio valores para getSentimentDocumentObject ");
			
			SentimentServiceStub.GetDocumentsbySentimentObjectResponse response = wp5
					.getDocumentsbySentimentObject(req);
			SentimentServiceStub.ScoreByDocument[] s = response.get_return();
			System.out.println("ALP=>response="+response);
			logger.info("ALP=>response="+response);
					
			if (s != null) {
				for (SentimentServiceStub.ScoreByDocument sent : s) { // Just x first news
					
					docu = sent.getDocument();
					if (docu != null) {
						SentimentDoc sd = new SentimentDoc();

						sd.setUrl(docu.getUrl());
						sd.setSource(new URL(docu.getUrl()).getHost());
						sd.setTitle(docu.getTitle());
						sd.setInternalLink("drilldown?docId=" + docu.getId());
						sd.setId(docu.getId());
						sd.setDate(docu.getPublicationDate());
						sd.setScore(sent.getScore());
						
						SingleRow sr = new SingleRow(sd);
						vo.addComponent(sr);
					}
					
					
					//highlighting new news rows - works with refresher 
					/*
					if (hashList.size() == 10) {
						if (hashList.keySet().contains(sd.getTitle()) == false) {
							sr.setStyleName("new-document");
							
							hashList.remove(docList.getFirst().getTitle());
							docList.removeFirst();
						}			
					}
					docList.add(sd);
					hashList.put(sd.getTitle(), sd);
					*/
				}				
				//this.error = false;
			} else {
				vo.removeAllComponents();
				errorRow = new SingleRow(new Label("Sorry! No documents were found for this object."));
				this.noDocs = true;
				vo.addComponent(errorRow);
			}
			this.error = false;
			
		} catch (SentimentServiceParseExceptionException0 e ) {
			vo.removeAllComponents();
			errorRow = new SingleRow(new Label("This is embarrasing! Our servers were so busy that they couldn't reply on time. Please wait for a while and try again!"));
			this.error = true;
			vo.addComponent(errorRow);

			e.printStackTrace();
		} catch (AxisFault e) {
			vo.removeAllComponents();
			errorRow = new SingleRow(new Label("This is embarrasing! Our servers were so busy that they couldn't reply on time. Please wait for a while and try again!"));
			this.error = true;
			vo.addComponent(errorRow);

			e.printStackTrace();
		} catch (RemoteException e) {
			vo.removeAllComponents();
			errorRow = new SingleRow(new Label("This is embarrasing! Our servers were so busy that they couldn't reply on time. Please wait for a while and try again!"));
			this.error = true;
			vo.addComponent(errorRow);

			e.printStackTrace();
		} catch (MalformedURLException e) {
			vo.removeAllComponents();
			errorRow = new SingleRow(new Label("This is embarrasing! Our servers were so busy that they couldn't reply on time. Please wait for a while and try again!"));
			this.error = true;
			vo.addComponent(errorRow);

			e.printStackTrace();
		}
	}
	
	void calculaMaxPags(){
		int resul =0;
		int resto =0;
		int maxNews = f.getMaxNews();
		
		//get today's date:
		/*Calendar currentDate = Calendar.getInstance(); 
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd"); 
		String dateNow = formatter.format(currentDate.getTime());
		
		currentDate.add(Calendar.DATE, -7);
		String weekAgo = formatter.format(currentDate.getTime());*/
		
		try {
			SentimentServiceStub wp5 = new SentimentServiceStub(f.getWSDL());

			SentimentServiceStub.GetDocumentsbySentimentObjectCount req = new SentimentServiceStub.GetDocumentsbySentimentObjectCount();
			req.setSentiment_object_uri(URI);
			req.setSentiment_subfeature_uri(this.reputationTopic);
			req.setStartdate(day.toString().substring(0,f.getLength())); //weekAgo
			req.setEnddate(today.toString().substring(0,f.getLength())); //dateNow
			if (this.reputationTopic != null) {
				logger.info("reputation topic: " + this.reputationTopic);
				req.setSentiment_subfeature_uri(this.reputationTopic);
			}
			if (this.indicator != null) {
				logger.info("indicator: " + this.indicator);
				req.setSentiment_feature_uri(this.indicator);
			}
			System.out.println("ALP=>Inicio valores para GetDocumentsbySentimentObjectCount ");
			logger.info("ALP=>Inicio valores para GetDocumentsbySentimentObjectCount ");

			SentimentServiceStub.GetDocumentsbySentimentObjectCountResponse response = wp5
					.getDocumentsbySentimentObjectCount(req);
			numTotalDocs = response.get_return();
			logger.info("ALP=>numTotalDocs="+numTotalDocs);
			
			if(numTotalDocs > 0){
				if(numTotalDocs >= maxNews){		
					resul = numTotalDocs/maxNews;
					resto = numTotalDocs%maxNews;
				}else{
					resul=1; //Just 1 page
				}
				
				if(resto>0){
					numPags=resul+1;
				}else{
					numPags=resul;
				}
			}
		
	/*	} catch (SentimentServiceParseExceptionException0 e ) {
			vo.removeAllComponents();
			errorRow = new SingleRow(new Label("This is embarrasing! Our servers were so busy that they couldn't reply on time. Please wait for a while and try again!"));
			this.error = true;
			vo.addComponent(errorRow);
	        
			e.printStackTrace();*/
		} catch (AxisFault e) {
			vo.removeAllComponents();
			errorRow = new SingleRow(new Label("This is embarrasing! Our servers were so busy that they couldn't reply on time. Please wait for a while and try again!"));
			this.error = true;
			vo.addComponent(errorRow);
	
			e.printStackTrace();
		} catch (RemoteException e) {
			vo.removeAllComponents();
			errorRow = new SingleRow(new Label("This is embarrasing! Our servers were so busy that they couldn't reply on time. Please wait for a while and try again!"));
			this.error = true;
			vo.addComponent(errorRow);
	
			e.printStackTrace();
		}/* catch (MalformedURLException e) {
			vo.removeAllComponents();
			errorRow = new SingleRow(new Label("This is embarrasing! Our servers were so busy that they couldn't reply on time. Please wait for a while and try again!"));
			this.error = true;
			vo.addComponent(errorRow);
	
			e.printStackTrace();
		}*/
		
	}

	

}
