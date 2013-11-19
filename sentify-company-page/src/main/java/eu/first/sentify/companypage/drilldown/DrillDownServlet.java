package eu.first.sentify.companypage.drilldown;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DrillDownServlet extends HttpServlet {

	//String file = "17_45_24_8d68b7adcdaa4ac08f98d3f0649176a1_7f9d067f484c822672fd1fc4a8543b2f.txt";
	//String docId = "80264";
	String serviceLocation = "http://first-vm3.ijs.si:8088/retrieve-document-service/gettext?docId=";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		NewsDocument nd;
		if (request.getParameter("docId") == null) {
			
			response.sendRedirect("index.jsp");

		} else {
			String docId = request.getParameter("docId");
			
			nd = new NewsDocument(docId);
			//System.out.println(nd);
			
			String text = FileRetriever.readUrlToString(serviceLocation + docId);
			//System.out.println(text);
			
			//NewsText nt = new NewsText();
			//nt.setContent(text);
			nd.setContent(text);
			
			request.setAttribute("news", nd);
			request.setAttribute("html", nd.toHTMLString());
			RequestDispatcher dispatcher =
				      request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);	
			
		}
		
	}
}
