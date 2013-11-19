package eu.first.sentify.companypage.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.first.sentify.mappings.SentimentObjectsMappings;
import eu.first.sentify.search.model.Company;
import eu.first.sentify.search.model.SentimentObject;
import eu.first.sentify.searchsentimentobjects.CompanySearch;

public class OtherPageServlet extends HttpServlet {

	CompanySearch cs = CompanySearch.getInstance();
	SentimentObjectsMappings som = SentimentObjectsMappings.getInstance();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//get other parameters
		String so = request.getParameter("so");
		String fromDate = request.getParameter("from-date");
		String toDate = request.getParameter("to-date");
		String topic = request.getParameter("topic");
		
		//use default company (Google)
		if (request.getParameter("so") == null) {
			response.sendRedirect(request.getServletPath() + "?so=org_EU");

		} else {
			SentimentObject company = cs.getCompanyByURIFragment(request.getParameter("so"));
			if (company != null) {
				System.out.println("Company page invoked with parameter " + request.getParameter("so"));
				System.out.println(company.getName());
				System.out.println(company.getURI());
				
				System.out.println("Fetching DBPedia mapping...");
				String mapping = som.getSOMappingByURI(company.getURI());
				company.setMapping(mapping);
				if (mapping != null) {
					String concept = mapping.split("/")[mapping.split("/").length-1];
					company.setWikipedia("http://en.m.wikipedia.org/wiki/" + concept);					
				} else {
					//for compatibility
					company.setWikipedia("http://en.m.wikipedia.org/wiki/Google");
				}
				
				//company variable holds is the human redeable name of the sentiment object 
				request.setAttribute("company", company);
				//this is the sentiment object (URI Fragment) of the company
				request.setAttribute("so", so);
				request.setAttribute("fromDate", fromDate);
				request.setAttribute("toDate", toDate);
				request.setAttribute("topic", topic);
				
				//detect mode (company -> crisp, fuzzy or reputation)
				String servletMode = request.getServletPath();
				
				//use the default company page (crisp sentiment)
				String targetPage = "other.jsp";
				
				if (servletMode.endsWith("/other") == true) {
					targetPage = "other.jsp";
				}
				if (servletMode.endsWith("/other-fuzzy") == true) {
					targetPage = "other-fuzzy.jsp";
				}
				if (servletMode.endsWith("/other-fuzzy2") == true) {
					targetPage = "other-fuzzy2.jsp";
				}
//				if (servletMode.endsWith("/company-reputation") == true) {
//					targetPage = "company-reputation.jsp";
//				}
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(targetPage);
				dispatcher.forward(request, response);	
			} else {
				RequestDispatcher dispatcher =
					      request.getRequestDispatcher("company-notfound.jsp");
				dispatcher.forward(request, response);	
			}
	
		}
	
	}
	
}
