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


public class CompanySearchServlet extends HttpServlet {

	//Hashtable<String, String> companyMapping = new Hashtable<String, String>();
	//String allCompanies = null;

	//Trie<String, Company> trie = new PatriciaTrie<String, Company>(StringKeyAnalyzer.CHAR);

	//Model m;
	
	CompanySearch cs = CompanySearch.getInstance();
	SentimentObjectsMappings som = SentimentObjectsMappings.getInstance();

	public void init() {
		

	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//for internal quick URI 
		//this part is legacy. all classes should now directly use CompanySearch class
		PrintWriter out = response.getWriter();
		if (request.getParameter("uri") != null) {
			String companyName = cs.getCompanyNameByURIFragment(request.getParameter("uri"));
			if (companyName != null) {
				out.println(companyName);				
			}
//			PrintWriter out = response.getWriter();
//			String companyName = companyMapping.get("http://project-first.eu/ontology#"+request.getParameter("uri"));
//			if (companyName != null) {
//				out.println(companyName);				
//			}
			
		} else {
			if (request.getParameter("q") == null) {
				RequestDispatcher dispatcher =
					      request.getRequestDispatcher("index.jsp");
			} else {
				System.out.println("****");
				String query =  request.getParameter("q");
				System.out.println("q=" + query);
				
				//Map.Entry<String, Company> entry = trie.select(query);
				//System.out.println(entry.getKey());
				//System.out.println(entry.getValue().getName());
				//System.out.println(entry.getValue().getURI());
				//for (String symbol : entry.getValue().getSymbols()) {
				//	System.out.println(symbol);
				//}
				
				SentimentObject company = cs.searchCompany(query);
				System.out.println(company.getName());
				System.out.println(company.getURI());
				for (String symbol : company.getSymbols()) {
					System.out.println(symbol);					
				}
				System.out.println("Fetching mappings..");
				String mapping = som.getSOMappingByURI(company.getURI());
				company.setMapping(mapping);
				if (mapping != null) {
					String concept = mapping.split("/")[mapping.split("/").length-1];
					company.setWikipedia("http://en.m.wikipedia.org/wiki/" + concept);					
				} else {
					//for compatibility
					company.setWikipedia("http://en.m.wikipedia.org/wiki/Google");
				}
				
				//Company company = new Company();
				//company.setName(entry.getKey());
				//company.setURI(entry.getValue());
				//request.getParameterMap().put("company", new String[]{entry.getValue().getURI().split("#")[1]});
				if (company.getSymbol() != null) {
					response.sendRedirect("company?company=" + company.getURI().split("#")[1]);
					
					
				} else {
					request.setAttribute("company", company);
					RequestDispatcher dispatcher =
						      request.getRequestDispatcher("search.jsp");
					dispatcher.forward(request, response);			
					
				}
				
			}
			
		}
		
		

	}
}
