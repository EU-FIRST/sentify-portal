package eu.first.sentify.companypage.drilldown;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

public class FileRetriever {

	public static String readZipSambaFileToString(String path, String username, String pass, Charset cs) throws IOException {
		
		NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication("", username, pass);
	    SmbFile smbFile = new SmbFile(path, auth);
	    SmbFileInputStream smbfos = new SmbFileInputStream(smbFile);
		
		ZipInputStream zstream = new ZipInputStream(smbfos);
		
		try {
			ZipEntry ze = zstream.getNextEntry();
			
			Reader reader = new BufferedReader(new InputStreamReader(zstream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			zstream.closeEntry();
			zstream.close();
		}
		
	}
	
	
	public static String readUrlToString(String url) throws IOException {
		StringBuilder sb = new StringBuilder();

        URL serviceUrl = new URL(url);
        BufferedReader in = new BufferedReader(
        new InputStreamReader(serviceUrl.openStream()));

        int character;
        
        while ((character = in.read()) != -1)
            sb.append((char)character);
        in.close();
        
        return sb.toString();
	}
	
	public static String readZipFileToString(String file, Charset cs) throws IOException {
		//we assume the compressed file contains only 1 file, so we read
		
		ZipInputStream zstream = new ZipInputStream(new FileInputStream(file));
		try {
			ZipEntry ze = zstream.getNextEntry();
			
			Reader reader = new BufferedReader(new InputStreamReader(zstream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			zstream.closeEntry();
			zstream.close();
		}
	}
	
	
	public static String readFileToString(String file, Charset cs) throws IOException {

		
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {

			stream.close();
		}
	}
	
	
	public static String readResourceToString(String file, Charset cs) throws IOException {

		
		InputStream stream = FileRetriever.class.getResourceAsStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
//		} catch (Exception e) {
//			System.out.println(e);
		} finally {

			//stream.close();
		}
	}
}
