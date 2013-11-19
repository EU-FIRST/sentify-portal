package eu.first.sentify.companypage.search;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryparser.classic.ParseException;
import org.ardverk.collection.PatriciaTrie;
import org.ardverk.collection.StringKeyAnalyzer;
import org.ardverk.collection.Trie;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import eu.first.sentify.mappings.SentimentObjectsMappings;
import eu.first.sentify.search.model.Company;
import eu.first.sentify.search.model.SentimentObject;
import eu.first.sentify.searchsentimentobjects.CompanySearch;

public class CompanySearchServlet2 extends HttpServlet {

	CompanySearch cs = CompanySearch.getInstance();
	SentimentObjectsMappings som = SentimentObjectsMappings.getInstance();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
			ServletException {

		PrintWriter out = response.getWriter();
		HashMap<String, String> linkMap = new HashMap<String, String>();
		
		String debug = request.getParameter("debug");
		
		if (request.getParameter("q") != null) {
			ArrayList<SentimentObject> sentimentObjects;
			ArrayList<SentimentObject> filteredSentimentObjects = new ArrayList<SentimentObject>();
			try {
				sentimentObjects = cs.searchCompanyWildcards(request.getParameter("q"));

				//filter stocks. We don't want to duplicate stocks/companies and confuse end user with this regard				
				for (SentimentObject so : sentimentObjects) {
					if ((so.getType() != null && so.getType().contains("Stock") != true) || (debug != null && debug == "debug")) {
						filteredSentimentObjects.add(so);
					}
				
				}
				
				for (SentimentObject so : filteredSentimentObjects) {
					//System.out.println(so.getType());
					boolean set = false;
					if (so.getType().toLowerCase().contains("country")) {
						linkMap.put(so.getName(), "countries?so=" + so.getURIFragment());
						set = true;
					}
					if (so.getType().toLowerCase().contains("geographical")) {
						linkMap.put(so.getName(), "countries?so=" + so.getURIFragment());
						set = true;
					}
					if (so.getType().toLowerCase().contains("organization")) {
						linkMap.put(so.getName(), "other?so=" + so.getURIFragment());
						set = true;
					}
					if (so.getType().toLowerCase().contains("institution")) {
						linkMap.put(so.getName(), "other?so=" + so.getURIFragment());
						set = true;
					}
					if (so.getType().toLowerCase().contains("instrument")) {
						linkMap.put(so.getName(), "other?so=" + so.getURIFragment());
						set = true;
					}
					if (so.getType().toLowerCase().contains("central")) {
						linkMap.put(so.getName(), "other?so=" + so.getURIFragment());
						set = true;
					}
					if (so.getType().toLowerCase().contains("currency")) {
						linkMap.put(so.getName(), "other?so=" + so.getURIFragment());
						set = true;
					}
					if (set == false) {
						linkMap.put(so.getName(), "company?so=" + so.getURIFragment());
					}
					
					
				}
				
				request.setAttribute("results", filteredSentimentObjects);
				request.setAttribute("linkmap", linkMap);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			
			//complement returning object list with other properties (links, sparklines)
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("searchresults.jsp");
			dispatcher.forward(request, response);

		}

	}
}
