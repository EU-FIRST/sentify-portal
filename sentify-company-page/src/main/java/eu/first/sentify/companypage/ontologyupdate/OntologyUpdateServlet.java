package eu.first.sentify.companypage.ontologyupdate;

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

public class OntologyUpdateServlet extends HttpServlet {

	CompanySearch cs = CompanySearch.getInstance();
	SentimentObjectsMappings som = SentimentObjectsMappings.getInstance();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
				

		RequestDispatcher dispatcher =
			      request.getRequestDispatcher("update.jsp");
		dispatcher.forward(request, response);	

	

	
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		if (request.getParameter("file") == null || request.getParameter("file").isEmpty() == true) {
			request.setAttribute("message", "Ontology archive not found. Please provide URL with ontology archive!");
		} else {
			request.setAttribute("message", "Running Ontology update script...");
		}
		

		RequestDispatcher dispatcher =
			      request.getRequestDispatcher("update.jsp");
		dispatcher.forward(request, response);	

	

	
	}
}
