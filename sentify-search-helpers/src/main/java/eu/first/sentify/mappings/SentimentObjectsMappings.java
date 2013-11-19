package eu.first.sentify.mappings;

import java.util.Hashtable;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import eu.first.sentify.search.model.Company;
import eu.first.sentify.search.model.SentimentObject;


public class SentimentObjectsMappings {

	//mapping first ontology -> dbpedia
	Hashtable<String, String> countryMapping = new Hashtable<String, String>();

	Model m;
	
	private SentimentObjectsMappings() {

		System.out.println("Loading external mappings...");
		
		m = ModelFactory.createDefaultModel();
		m.read(this.getClass().getClassLoader().getResourceAsStream("mapping-countries-out.n3"), null, "N3");

		Property owlSameAs = m.createProperty("http://www.w3.org/2002/07/owl#sameAs");

		StmtIterator si = m.listStatements((Resource) null, owlSameAs, (RDFNode) null);
		int count = 0;
		while (si.hasNext()) {
			Statement s = si.next();
			
			//System.out.println(s.getObject().toString() + " -> " + s.getSubject().getURI());
			countryMapping.put(s.getSubject().getURI(), s.getObject().toString());
			count++;
		}
		System.out.println("Loaded " + count + " mappings.");
			

	}
	
    /**
    * SingletonHolder is loaded on the first execution of Singleton.getInstance() 
    * or the first access to SingletonHolder.INSTANCE, not before.
    */
    private static class SingletonHolder { 
            //public static final SentimentObjectsMappings INSTANCE = new SentimentObjectsMappings();
            private static final SentimentObjectsMappings INSTANCE;
            static {
                try {
                    INSTANCE = new SentimentObjectsMappings();
                } catch (Exception e) {
                    throw new ExceptionInInitializerError(e);
                }
            }
    }

    public static SentimentObjectsMappings getInstance() {
            return SingletonHolder.INSTANCE;
    }
    
    
    public String getSOMappingByFragment(String fragment) {
    	return countryMapping.get("http://project-first.eu/ontology#" + fragment);
    	
    }
    public String getSOMappingByURI(String uri) {
    	return countryMapping.get(uri);
    	
    }
    
    public Hashtable<String, String> getAllMappings() {
    	return countryMapping;
    }
}
