package eu.first.sentify.reputationtopics;

public enum SentimentIndicator {
	REPUTATION("http://project-first.eu/FIRSTOntology_ObjectFeature#Reputation", "reputation", "reputation"),
	PRIOCE("http://project-first.eu/FIRSTOntology_ObjectFeature#ExpectedFuturePriceChange", "ExpectedFuturePriceChange", "price");
	
	
	private final String URI;  
	private final String name; 
	private final String shortName;
	
	    
	SentimentIndicator(String URI, String name, String shortName) {
		this.URI = URI;
		this.name = name;
		this.shortName = shortName;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getURI() {
		return URI;
	}

	public String getName() {
		return name;
	}
	
	public String getShortName() {
		return shortName;
	}
	
	public SentimentIndicator getReputationURIByName() {
		return null;
		
	}
}
