package eu.first.sentify.companypage.drilldown;

import java.rmi.RemoteException;

import eu.first.commons.ws.client.SentimentServiceStub;
import eu.first.commons.ws.client.SentimentServiceStub.DocumentVersion;
import eu.first.commons.ws.client.SentimentServiceStub.GetDocumentVersions;
import eu.first.commons.ws.client.SentimentServiceStub.GetDocumentVersionsResponse;
import eu.first.commons.ws.client.SentimentServiceStub.Url;
import eu.first.sentify.configuration.Functions;

public class ServiceUtils {

	static Functions f = new Functions();
	
	/*
	 * Returns the path to the document file, as stored in the DB.
	 * It can be either URL to smb resource (smb://...) or simply
	 * relative path (for older documents). We handle both.
	 * Update: currently we use only filePath, as documents are
	 * spread over many machines inconsistently.
	 * 
	 */
	public static String getUrlById(String docId) throws RemoteException {
		String filePath = null;
		
		SentimentServiceStub wp5 = new SentimentServiceStub(f.getWSDL());
		
		GetDocumentVersions req1 = new SentimentServiceStub.GetDocumentVersions();
		req1.setDocument_id(Long.parseLong(docId));
		req1.setLimit(10);
		req1.setOffset(0);
		
		GetDocumentVersionsResponse response1 = wp5.getDocumentVersions(req1);

		DocumentVersion[] dvs = response1.get_return();
		if ((dvs != null) && (dvs.length > 0)) {
			//simple hack: we iterate to get to the last document version, which really matters.
			for (DocumentVersion dv : dvs) {
				if (dv.getUrl() != null) {
					Url url = dv.getUrl();
					filePath = url.getPath();
					
//					if ((url.getHost() != null) && (url.getProtocol() != null)) {
//						filePath = url.getProtocol() + url.getHost() + filePath;
//					}
					
				}
				
			}
		}
		
		
		return filePath;
	}
}
