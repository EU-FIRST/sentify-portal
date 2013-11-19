package eu.first.sentify.companypage.drilldown;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.axis2.AxisFault;

import eu.first.commons.ws.client.SentimentServiceStub;
import eu.first.commons.ws.client.SentimentServiceStub.DocumentMetaData;
import eu.first.commons.ws.client.SentimentServiceStub.GetDocumentMetadata;
import eu.first.commons.ws.client.SentimentServiceStub.GetDocumentMetadataResponse;
import eu.first.commons.ws.client.SentimentServiceStub.GetPhrases;
import eu.first.commons.ws.client.SentimentServiceStub.GetSentimentDocument;
import eu.first.commons.ws.client.SentimentServiceStub.GetSentimentDocumentObject;
import eu.first.commons.ws.client.SentimentServiceStub.GetSentimentDocumentResponse;
import eu.first.commons.ws.client.SentimentServiceStub.GetSentimentObjectsbyDocument;
import eu.first.commons.ws.client.SentimentServiceStub.GetSentimentObjectsbyDocumentResponse;
import eu.first.commons.ws.client.SentimentServiceStub.GetSentimentPhrasebyDocumentALL;
import eu.first.commons.ws.client.SentimentServiceStub.GetSentimentPhrasebyDocumentALLResponse;
import eu.first.commons.ws.client.SentimentServiceStub.Phrase;
import eu.first.commons.ws.client.SentimentServiceStub.Sentiment;
import eu.first.commons.ws.client.SentimentServiceStub.SentimentObject;
import eu.first.commons.ws.client.SentimentServiceStub.TrendingTopic;
import eu.first.sentify.configuration.Functions;

public class NewsDocument {

	String title;
	Date publicationdate;
	Date retrievaldate;
	String content;
	String url;
	String author;
	
	
	Long id;
	
	ArrayList<NewsPhrase> phrases = new ArrayList<NewsPhrase>();
	ArrayList<SentimentObjectConcept> sentimentObjects = new ArrayList<SentimentObjectConcept>();
	
	Functions f = new Functions();
	
	public NewsDocument(String docId) throws RemoteException {
		
		SentimentServiceStub wp5 = new SentimentServiceStub(f.getWSDL());
		
		//1st invocation - get metadata
		GetDocumentMetadata req0 = new SentimentServiceStub.GetDocumentMetadata();
		req0.setDocument_id(Long.parseLong(docId));

		GetDocumentMetadataResponse response0 = wp5.getDocumentMetadata(req0);
		DocumentMetaData metadata = response0.get_return();
		this.title = metadata.getTitle();
		this.author = metadata.getAuthor();
		this.publicationdate = metadata.getPublicationDate();
		this.retrievaldate = metadata.getRetrievalDate();
		this.id = metadata.getId();
		this.url = metadata.getUrl();
//		
		//2nd invocation - retrieve all sentiment object detected in the document
		GetSentimentObjectsbyDocument req1 = new SentimentServiceStub.GetSentimentObjectsbyDocument();
		req1.setDocument_id(Long.parseLong(docId));
		
		GetSentimentObjectsbyDocumentResponse response1 = wp5.getSentimentObjectsbyDocument(req1);
		TrendingTopic[] s = response1.get_return();
		if ((s != null) && (s.length > 0)) {
			for (int i = 0; i < s.length; i++) {

				TrendingTopic sent = s[i];

				SentimentObject so = sent.getTopic();
				
				SentimentObjectConcept soc = new SentimentObjectConcept();
				soc.setName(so.getName());
				soc.setURI(so.getOntologyConceptUri());
				sentimentObjects.add(soc);
				
				//3rd invocation (iterative) - for each sentiment object retrieve corresponding phrases
				GetSentimentPhrasebyDocumentALL req2 = new SentimentServiceStub.GetSentimentPhrasebyDocumentALL();
				req2.setDocument_id(Long.parseLong(docId));
				req2.setSentiment_object_uri(soc.getURI());
				
				GetSentimentPhrasebyDocumentALLResponse response2 = wp5.getSentimentPhrasebyDocumentALL(req2);
				Phrase[] p = response2.get_return();
				if ((p != null) && (p.length > 0)) {
					for (int j = 0; j < p.length; j++) {
						Phrase phrase = p[j];
						
						NewsPhrase np = new NewsPhrase();
						np.setStartNode(phrase.getStartnode());
						np.setEndNode(phrase.getEndnode());
						np.setText(phrase.getText());
						np.setId(phrase.getId());
						np.setPhraseType(phrase.getPhraseType().getName());
						np.setPhraseTypeId(phrase.getPhraseType().getId());
						np.setSentimentScore(phrase.getSentimentScore());
						
						phrases.add(np);
						
					}
				}
				
			}
			
		}
		

		System.out.println(this.toString());
	}

	public String toHTMLString() {
		StringBuilder sb = new StringBuilder();
		
		LinkedList<Integer> st = new LinkedList<Integer>();
		LinkedList<Integer> et = new LinkedList<Integer>();
		
		String starttagS = "<span class=\"highlight\">";
		String starttagSE = "<span class=\"highlight-se\">";
		String starttagF = "<span class=\"highlight-f\">";
		String endtag = "</span>";
		
		
		//int stLen = starttag.length();
		//int etLen = endtag.length();
		
		
		HashMap<Integer, ArrayList<NewsPhrase>> phraseMap = new HashMap<Integer, ArrayList<NewsPhrase>>();
		ArrayList<Integer> keys = new ArrayList<Integer>();
		
		for (NewsPhrase nf: phrases) {
			//we are interested only in types: 6, 7, 10
			if (nf.getPhraseTypeId() == 7 || nf.getPhraseTypeId() == 6 || nf.getPhraseTypeId() == 10) {
				Integer startnode = nf.getStartNode();
				Integer endnode = nf.getEndNode();
				
				//keys.add(startnode);
				//keys.add(endnode);

				ArrayList<NewsPhrase> lnp = phraseMap.get(startnode);
				if (lnp == null) {
					lnp = new ArrayList<NewsPhrase>();
					phraseMap.put(startnode, lnp);
				}
				lnp.add(nf);
				
				lnp = phraseMap.get(endnode);
				if (lnp == null) {
					lnp = new ArrayList<NewsPhrase>();
					phraseMap.put(endnode, lnp);
				}
				lnp.add(nf);
				
				keys.add(startnode);
				keys.add(endnode);

			}
			

		}
		//ArrayList<Integer> keys = new ArrayList<Integer>(phraseMap.keySet());
		java.util.Collections.sort(keys);
		
		//System.out.println(keys);
		
		//highlight
		int offsetCount = 0;
		int lastIndex = 0;
		for (Integer key : keys) {
			ArrayList<NewsPhrase> npl = phraseMap.get(key);
			
			for (NewsPhrase np : npl) {
				if (key == np.getStartNode()) {
					sb.append(this.content.substring(lastIndex, key));
					/*
					if (np.getPhraseTypeId() == 7) {
						sb.append(starttagSE);
						//System.out.print("* " + np.getStartNode());
						//System.out.print(" -> " + np.getEndNode());
						//System.out.println(" : " + np.getText());
						
					} 
					*/
					if (np.getPhraseTypeId() == 6) {
						sb.append(starttagS);

						//System.out.print("* " + np.getStartNode());
						//System.out.print(" -> " + np.getEndNode());
						//System.out.println(" : " + np.getText());

					}
					
					//no need for this type
//					if (np.getPhraseTypeId() == 10) {
//						sb.append(starttagF);
//
//						//System.out.print("* " + np.getStartNode());
//						//System.out.print(" -> " + np.getEndNode());
//						//System.out.println(" : " + np.getText());
//
//					}
					//offsetCount += stLen;
					lastIndex = key;
				} 
				if (key == np.getEndNode()) {
					sb.append(this.content.substring(lastIndex, key));
					sb.append(endtag);
					
					//System.out.print("" + np.getStartNode());
					//System.out.print(" -> * " + np.getEndNode());
					//System.out.println(" : " + np.getText());

					//offsetCount += etLen;
					lastIndex = key;
				}
			}
			
			
			
		}
		//append the rest
		sb.append(this.content.substring(lastIndex, this.content.length()));
		
		
		return sb.toString();
	}
	
	private int getTagsNumBefore(LinkedList<Integer> list, int position) {
		
		return 0;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPublicationdate() {
		return publicationdate;
	}

	public void setPublicationdate(Date publicationdate) {
		this.publicationdate = publicationdate;
	}

	public Date getRetrievaldate() {
		return retrievaldate;
	}

	public void setRetrievaldate(Date retrievaldate) {
		this.retrievaldate = retrievaldate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ArrayList<NewsPhrase> getPhrases() {
		return phrases;
	}

	public ArrayList<SentimentObjectConcept> getSentimentObjects() {
		return sentimentObjects;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(this.title + "\n");
		sb.append(this.publicationdate + "\n");
		sb.append(this.author + "\n");
		sb.append(this.retrievaldate + "\n");
		sb.append(this.content + "\n");
		sb.append(this.sentimentObjects + "\n");
		sb.append(this.phrases + "\n");
		
		return sb.toString();
	}

}
