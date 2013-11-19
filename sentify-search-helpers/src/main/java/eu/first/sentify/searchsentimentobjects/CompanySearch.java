package eu.first.sentify.searchsentimentobjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
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

public class CompanySearch {
	
	//mapping sentiment object uri -> company obj
	//Hashtable<String, Company> companyMapping = new Hashtable<String, Company>();
	Hashtable<String, SentimentObject> soMapping = new Hashtable<String, SentimentObject>();
	
	//get mappings of sentiment objects to external datasets (dbpedia)
	SentimentObjectsMappings som = SentimentObjectsMappings.getInstance();
	
	String allCompanies = null;

	//Patricia Trie structure for text search (basic)
	Trie<String, SentimentObject> trie = new PatriciaTrie<String, SentimentObject>(StringKeyAnalyzer.CHAR);
	
	//lucene stuff (advanced search)
	StandardAnalyzer analyzer;
	Directory index;
	
	//jena rdf model for reading ontology
	Model m;
	
	//onto loc
	String ontoDir = "onto-v2/";
	
	private CompanySearch() { 
		
		try {
			

			//read country mappings
			m = ModelFactory.createDefaultModel();
//			m.read(this.getClass().getClassLoader().getResourceAsStream("company.n3"), null, "N3");
//			m.read(this.getClass().getClassLoader().getResourceAsStream("fi_gaz.n3"), null, "N3");
//			m.read(this.getClass().getClassLoader().getResourceAsStream("geo_inst.n3"), null, "N3");
//			m.read(this.getClass().getClassLoader().getResourceAsStream("geo_curr.n3"), null, "N3");
//			m.read(this.getClass().getClassLoader().getResourceAsStream("geo_org.n3"), null, "N3");
//			m.read(this.getClass().getClassLoader().getResourceAsStream("geo_capital.n3"), null, "N3");
			
			m.read(this.getClass().getClassLoader().getResourceAsStream("y3/First/company.n3"), null, "N3");
			m.read(this.getClass().getClassLoader().getResourceAsStream("y3/First/fi_gaz.n3"), null, "N3");
			m.read(this.getClass().getClassLoader().getResourceAsStream("y3/First/fi.n3"), null, "N3");
			m.read(this.getClass().getClassLoader().getResourceAsStream("y3/Geo/geo_inst.n3"), null, "N3");
			m.read(this.getClass().getClassLoader().getResourceAsStream("y3/Geo/geo_curr.n3"), null, "N3");
			m.read(this.getClass().getClassLoader().getResourceAsStream("y3/Geo/geo_org.n3"), null, "N3");
			m.read(this.getClass().getClassLoader().getResourceAsStream("y3/Geo/geo_capital.n3"), null, "N3");
			m.read(this.getClass().getClassLoader().getResourceAsStream("y3/Geo/geo_city.n3"), null, "N3");
			m.read(this.getClass().getClassLoader().getResourceAsStream("y3/SovereignDebt/protagonist.n3"), null, "N3");
			m.read(this.getClass().getClassLoader().getResourceAsStream("y3/SovereignDebt/eurocrisis_topLevel.n3"), null, "N3");
			m.read(this.getClass().getClassLoader().getResourceAsStream("y3/SovereignDebt/eurocrisis_entities_glo.n3"), null, "N3");
			m.read(this.getClass().getClassLoader().getResourceAsStream("y3/SovereignDebt/cryptocurrency.n3"), null, "N3");
			m.read(this.getClass().getClassLoader().getResourceAsStream("y3/SovereignDebt/core_eurocrisis.n3"), null, "N3");
			
			
			Property label = m.createProperty("http://www.w3.org/2000/01/rdf-schema#label");
			Property type = m.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
			Property issues = m.createProperty("http://project-first.eu/ontology#issues");
			Property identifiedBy = m.createProperty("http://project-first.eu/ontology#identifiedBy");
			Property term = m.createProperty("http://project-first.eu/ontology#term");
			
			StmtIterator si = m.listStatements((Resource) null, label, (RDFNode) null);
			
			StringBuilder sb = new StringBuilder();
			
			//lucene index initialisation
			analyzer = new StandardAnalyzer(Version.LUCENE_41);
			index = new RAMDirectory();
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_41, analyzer);
			IndexWriter w = new IndexWriter(index, config);
			
			int i = 0;
			HashMap<String, String> typeMap = new HashMap<String, String>();
			while (si.hasNext()) {
				Statement s = si.next();
	
				//identify type
				StmtIterator types = m.listStatements(s.getSubject(), type, (RDFNode) null);
				String soType = "";
				while (types.hasNext()) {
					Statement t = types.next();
					soType = t.getObject().toString().replace("http://project-first.eu/ontology#", "");
					typeMap.put(t.getObject().toString(), t.getSubject().toString());
//					if (t.getObject().toString().contains("Other") == true) {
//						System.out.println(t.getObject().toString());
//						System.out.println(t.getSubject().toString());
//						
//					}
				}
				
				SentimentObject so = new SentimentObject();
				so.setName(s.getObject().toString());
				so.setURI(s.getSubject().getURI());
				so.setURIFragment(s.getSubject().toString().replace("http://project-first.eu/ontology#", ""));
				so.setType(soType);
				
				soMapping.put(s.getSubject().getURI(), so);
				
				//get stock symbol
				//we do it for all types, as some companies are of type "Other"
				GenericSPARQLQuery gsq = new GenericSPARQLQuery();
				gsq.setModel(this.m);
				ArrayList<HashMap<String, RDFNode>> resultSet;
				try {
					resultSet = gsq.queryModel("SELECT  ?term\r\n" + 
							" WHERE {\r\n" + 
							" ?s <http://project-first.eu/ontology#issues> ?o .\r\n" + 
							" ?o <http://project-first.eu/ontology#identifiedBy> ?stock .\r\n" + 
							" ?stock <http://project-first.eu/ontology#term> ?term\r\n" + 
							"FILTER (?s = <"+s.getSubject().getURI()+">)\r\n" + 
							"} LIMIT 100");	
					for (HashMap<String, RDFNode> hm : resultSet) {
						so.addSymbol(hm.get("term").toString());
					}
					//nasty hack! TODO shuld be corrected in the knowledgebase
					if (so.getSymbols().size()>0) {
						so.setSymbol(so.getSymbols().get(so.getSymbols().size()-1));
					}
				} catch (Exception e) {
					System.out.println(e);
				}
				
				//find mappings
				String mapping = som.getSOMappingByURI(so.getURI());
				so.setMapping(mapping);
				
				//set wikipedia link
				if (mapping != null) {
					String concept = mapping.split("/")[mapping.split("/").length-1];
					so.setWikipedia("http://en.m.wikipedia.org/wiki/" + concept);					
				}
				
				String lowercaseName = s.getObject().toString().toLowerCase();
			
				
				//companyMapping.put(obj, subj);
				
				trie.put(lowercaseName, so);
				addDoc(w, lowercaseName, so.getURI());
				//sb.append(obj);
				i++;
			}
			//System.out.println(typeMap.keySet());

			System.out.println("Loaded: " + i + " ontology objects");
			System.out.println("Loaded: " + typeMap.size() + " classes");
			w.close();
			//allCompanies = sb.toString();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		
		
	}
	 
	
	private static void addDoc(IndexWriter w, String name, String companyURI)
			throws IOException {
		Document doc = new Document();
		doc.add(new TextField("name", name, Store.YES));
		doc.add(new StringField("company", companyURI, Store.YES));
		w.addDocument(doc);
	}
	
    /**
    * SingletonHolder is loaded on the first execution of Singleton.getInstance() 
    * or the first access to SingletonHolder.INSTANCE, not before.
    */
    private static class SingletonHolder { 
            public static final CompanySearch INSTANCE = new CompanySearch();
    }

    public static CompanySearch getInstance() {
            return SingletonHolder.INSTANCE;
    }
    
    public SentimentObject searchCompany(String query) {
    	Map.Entry<String, SentimentObject> entry = trie.select(query);
    	return entry.getValue();
    	
    }
    
    public String getCompanyNameByURIFragment(String fragment) {
    	SentimentObject so = soMapping.get("http://project-first.eu/ontology#"+fragment);
    	if (so == null) {
    		return null;
    	} else {
    		return so.getName();
    	}
    	
    }
    
    public SentimentObject getCompanyByURIFragment(String fragment) {
    	SentimentObject so = soMapping.get("http://project-first.eu/ontology#"  + fragment);
    	return so;
    }
    
    public String getCompanyNameByURI(String uri) {
    	SentimentObject so = soMapping.get(uri);
    	if (so == null) {
    		return null;
    	} else {
    		return so.getName();
    	}
    }
    
	public ArrayList<SentimentObject> searchCompanyWildcards(String query) throws ParseException, IOException {
		ArrayList<SentimentObject> result = new ArrayList<SentimentObject>();
		
		String querystr = query + "*";
		Query q = new QueryParser(Version.LUCENE_41, "name", analyzer)
				.parse(querystr);

		int hitsPerPage = 10;
		DirectoryReader reader = DirectoryReader.open(index);
		IndexSearcher searcher = new IndexSearcher(reader);
		TopScoreDocCollector collector = TopScoreDocCollector.create(
				hitsPerPage, true);
		searcher.search(q, collector);
		ScoreDoc[] hits = collector.topDocs().scoreDocs;
		
		System.out.println("Found " + hits.length + " hits.");
		
		for (int i = 0; i < hits.length; ++i) {
			int docId = hits[i].doc;
			Document d = searcher.doc(docId);
			System.out.println((i + 1) + ". " + d.get("name") + "\t" + d.get("company"));
			result.add(soMapping.get(d.get("company")));
		}
		
		return result;
	}

}
