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

public class CountriesMapServlet extends HttpServlet {

	CompanySearch cs = CompanySearch.getInstance();
	SentimentObjectsMappings som = SentimentObjectsMappings.getInstance();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		if (request.getParameter("country") == null) {
			//RequestDispatcher dispatcher = request.getRequestDispatcher("company.jsp");
			//response.sendRedirect("/");

//			Company company = cs.searchCompany("Microsoft");
//			
//			request.setAttribute("company", company);
//			RequestDispatcher dispatcher =
//				      request.getRequestDispatcher("company.jsp");
//			dispatcher.forward(request, response);		
			
			response.sendRedirect("countries.jsp");

		} else {
			SentimentObject company = cs.getCompanyByURIFragment(request.getParameter("country"));
			if (company != null) {
				System.out.println("Country page invoked with parameter " + request.getParameter("country"));
				System.out.println(company.getName());
				System.out.println(company.getURI());
				
				System.out.println("Fetching DBPedia mapping...");
				String mapping = som.getSOMappingByFragment(request.getParameter("country"));
				company.setMapping(mapping);
				if (mapping != null) {
					String concept = mapping.split("/")[mapping.split("/").length-1];
					company.setWikipedia("http://en.m.wikipedia.org/wiki/" + concept);					
				}
				
				request.setAttribute("country", company);
				RequestDispatcher dispatcher =
					      request.getRequestDispatcher("countries.jsp");
				dispatcher.forward(request, response);	
			} else {
				RequestDispatcher dispatcher =
					      request.getRequestDispatcher("countries.jsp");
				dispatcher.forward(request, response);	
			}
	
		}
		
		
//		if (request.getParameter("company") == null) {
//			//RequestDispatcher dispatcher = request.getRequestDispatcher("company.jsp");
//			response.sendRedirect("/");
//
//		} else {
//			System.out.println("****");
//			String query =  request.getParameter("company");
//			System.out.println("q=" + query);
//			
//			Company company = new Company();
//			company.setName(getCompanyName(request.getParameter("company")));
//			company.setURI(request.getParameter("company"));
//			
//			request.setAttribute("company", company);
//			RequestDispatcher dispatcher =
//				      request.getRequestDispatcher("company.jsp");
//			dispatcher.forward(request, response);			
//		}
	
	
	}
	
//	private String getCompanyName(String URI) {
//		String name = null;
//        
//        try {
//        	URL url = new URL("http://localhost:8080/sentify-company-page/search?uri="+URI);
//            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
//
//            String soRealName;
//            if ((soRealName = in.readLine()) != null)
//				name = soRealName;
//            in.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        return name;
//	}
}
