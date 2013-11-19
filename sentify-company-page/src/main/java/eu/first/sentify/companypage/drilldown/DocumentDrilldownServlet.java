package eu.first.sentify.companypage.drilldown;

import java.io.IOException;
import java.rmi.RemoteException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DocumentDrilldownServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
		//String serviceLocation = "http://first-vm3.ijs.si:8088/retrieve-document-service/gettext?docId=";
		NewsDocument nd;
		if (request.getParameter("docId") == null) {
			
			response.sendRedirect("./");

		} else {
			String docId = request.getParameter("docId");
			
			try {
				String content = DocumentFileLookup.lookupFile(docId);
				//System.out.println(content);
				//request.setAttribute("html", content);
				
				nd = new NewsDocument(docId);
				//String text = FileRetriever.readUrlToString(serviceLocation + docId);
				//System.out.println(text);
				
				nd.setContent(content);
				
				request.setAttribute("news", nd);
				request.setAttribute("html", nd.toHTMLString());
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("html", "Error retriving document");

			} finally {
				RequestDispatcher dispatcher =
					      request.getRequestDispatcher("drilldown.jsp");
				dispatcher.forward(request, response);	
			}
			
			


			
		}
		
	}
}
