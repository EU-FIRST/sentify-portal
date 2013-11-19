package eu.first.sentify.companypage.country;

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

public class CountryPageServlet extends HttpServlet {

	CompanySearch cs = CompanySearch.getInstance();
	SentimentObjectsMappings som = SentimentObjectsMappings.getInstance();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String so = request.getParameter("so");
		String fromDate = request.getParameter("from-date");
		String toDate = request.getParameter("to-date");
		String topic = request.getParameter("topic");
		
		if (request.getServletPath().endsWith("/world")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("world.jsp");
			dispatcher.forward(request, response);	
		} else {
			if (request.getParameter("so") == null) {
				response.sendRedirect(request.getServletPath() + "?so=cou_GR");

			} else {
				SentimentObject company = cs.getCompanyByURIFragment(request.getParameter("so"));
				if (company != null) {
					System.out.println("Country page invoked with parameter " + request.getParameter("so"));
					System.out.println(company.getName());
					System.out.println(company.getURI());
					
					System.out.println("Fetching DBPedia mapping...");
					String mapping = som.getSOMappingByFragment(request.getParameter("so"));
					company.setMapping(mapping);
					if (mapping != null) {
						String concept = mapping.split("/")[mapping.split("/").length-1];
						company.setWikipedia("http://en.m.wikipedia.org/wiki/" + concept);					
					}
					
					request.setAttribute("country", company);
					request.setAttribute("so", so);
					request.setAttribute("fromDate", fromDate);
					request.setAttribute("toDate", toDate);
					request.setAttribute("topic", topic);
					
					//detect mode (company -> crisp, fuzzy or reputation)
					String servletMode = request.getServletPath();
					
					//use the default company page (crisp sentiment)
					String targetPage = "country.jsp";
					
					if (servletMode.endsWith("/countries") == true) {
						targetPage = "country.jsp";
					}
					if (servletMode.endsWith("/countries-fuzzy") == true) {
						targetPage = "country-fuzzy.jsp";
					}
					if (servletMode.endsWith("/countries-fuzzy2") == true) {
						targetPage = "country-fuzzy2.jsp";
					}
					RequestDispatcher dispatcher = request.getRequestDispatcher(targetPage);
					dispatcher.forward(request, response);	
				} else {
					RequestDispatcher dispatcher =
						      request.getRequestDispatcher("country-notfound.jsp");
					dispatcher.forward(request, response);	
				}
		
			}
		}

	}
}
