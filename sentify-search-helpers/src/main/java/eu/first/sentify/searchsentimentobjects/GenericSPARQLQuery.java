package eu.first.sentify.searchsentimentobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;

public class GenericSPARQLQuery {

	private String endpoint = null;
	private Model model = null;
	
	public GenericSPARQLQuery() {

	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	
	public void setModel(Model model) {
		this.model = model;
	}

	public ArrayList<HashMap<String, RDFNode>> queryEndpoint(String query) throws Exception {
		//String query = "select ?s ?p\r\n" + "where {\r\n"
		//		+ " <http://dbpedia.org/resource/ISO_3166-1:" + code
		//		+ "> ?s ?p .\r\n" + "}\r\n" + "";
		// System.out.println(query);
		if (this.endpoint == null) {
			throw(new Exception("Set SPARQL endpoint or model first"));
		}
		
		Query sparql = QueryFactory.create(query);
		QueryExecution qexec = QueryExecutionFactory.sparqlService(this.endpoint, sparql);

		ResultSet results = qexec.execSelect();
		
		ArrayList<HashMap<String, RDFNode>> data = new ArrayList<HashMap<String, RDFNode>>();
		while (results.hasNext()) {
			HashMap<String, RDFNode> row = new HashMap<String, RDFNode>();
			QuerySolution soln = results.nextSolution();
			Iterator<String> ite = soln.varNames();
			while (ite.hasNext()) {
				String var = ite.next();
				//String x = soln.get(var).toString();
				RDFNode x = soln.get(var);
				row.put(var, x);
			}
			data.add(row);
		}

		return data;
	}
	
	public ArrayList<HashMap<String, RDFNode>> queryModel(String query) throws Exception {
		//String query = "select ?s ?p\r\n" + "where {\r\n"
		//		+ " <http://dbpedia.org/resource/ISO_3166-1:" + code
		//		+ "> ?s ?p .\r\n" + "}\r\n" + "";
		// System.out.println(query);
		if (this.model == null) {
			throw(new Exception("Set model first"));
		}
		
		Query sparql = QueryFactory.create(query);
		QueryExecution qexec = QueryExecutionFactory.create(sparql, this.model);

		ResultSet results = qexec.execSelect();
		
		ArrayList<HashMap<String, RDFNode>> data = new ArrayList<HashMap<String, RDFNode>>();
		while (results.hasNext()) {
			HashMap<String, RDFNode> row = new HashMap<String, RDFNode>();
			QuerySolution soln = results.nextSolution();
			Iterator<String> ite = soln.varNames();
			while (ite.hasNext()) {
				String var = ite.next();
				//String x = soln.get(var).toString();
				RDFNode x = soln.get(var);
				row.put(var, x);
			}
			data.add(row);
		}

		return data;
	}
}
