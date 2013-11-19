package eu.first.sentify.companypage.drilldown;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;



public class DocumentFileLookup {

	
	static String lookupFile(String documentId) throws IOException {
		
		String pathPrefix = "c:/users/lilli/first_documents_data/";
		String sambaPathPrefixVM3 = "smb://first-vm3.ijs.si/";
		String sambaPathPrefixKTHYPER = "smb://kt-hyper.ijs.si/";
		
	

		String filePath = ServiceUtils.getUrlById(documentId);
		if (filePath != null) {
			
		} else {
			
		}
		
		if (filePath.startsWith("/first/") == true) {
			filePath = filePath.replace("/first/", sambaPathPrefixVM3 + "first/");
			
		}
		
		if (filePath.startsWith("\\first\\") == true) {
			filePath = filePath.replace("\\first\\", sambaPathPrefixVM3 + "first/");
			
		}

		if (filePath.startsWith("/ARCHIVE_DOCUMENTS/") == true) {
			filePath = filePath.replace("/ARCHIVE_DOCUMENTS/", sambaPathPrefixVM3 + "ARCHIVE_DOCUMENTS/");
			
		}
		
		if (filePath.startsWith("\\archive_documents\\") == true) {
			filePath = filePath.replace("\\archive_documents\\", sambaPathPrefixVM3 + "ARCHIVE_DOCUMENTS/");
			
		}
		
		if (filePath.startsWith("/first-vm3.ijs.si/first/") == true) {
			filePath = filePath.replace("/first-vm3.ijs.si/first/", sambaPathPrefixVM3 + "first/");
			
		}
			
		if (filePath.startsWith("/documents_archive/") == true) {
			filePath = filePath.replace("/documents_archive/", sambaPathPrefixKTHYPER + "documents_archive/");
			
		}
		
		//check zip extension
		if (filePath.endsWith("zip") == false) {
			filePath = filePath + ".zip";
		} 
		
					
		System.out.println(filePath);
		String fileContent = null;
		
		if (filePath.startsWith("smb://first-vm3.ijs.si/")) {
			System.out.println("Reading from VM3...");
			fileContent = FileRetriever.readZipSambaFileToString(filePath, "mateusz", "mateusz", Charset.forName("UTF-8"));

		}
		
		if (filePath.startsWith("smb://kt-hyper.ijs.si/")) {
			System.out.println("Reading from KT-HYPER...");
			fileContent = FileRetriever.readZipSambaFileToString(filePath, "mateusz", "DramaAcc123", Charset.forName("UTF-8"));
		}
			
		return fileContent;

	}
}
