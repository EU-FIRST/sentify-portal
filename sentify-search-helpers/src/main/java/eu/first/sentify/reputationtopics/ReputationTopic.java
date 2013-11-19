package eu.first.sentify.reputationtopics;

import java.util.HashMap;


public enum ReputationTopic {

	BUSINESS_BEHAVIOUR("http://project-first.eu/FIRSTOntology_INDICATOR.owl#Reputation_Indicator_Business_Behavior", "Reputation_Indicator_Business_Behavior", "bb"),
	SOCIAL_RESPONSIBILITY("http://project-first.eu/FIRSTOntology_INDICATOR.owl#Reputation_Indicator_Exposure_on_Critical_Markets", "Reputation_Indicator_Exposure_on_Critical_Markets", "ecm"),
	EXPOSURE_ON_CRITICAL_MARKETS("http://project-first.eu/FIRSTOntology_INDICATOR.owl#Reputation_Indicator_Human_Resources", "Reputation_Indicator_Human_Resources", "hr"),
	HUMAN_RESOURCES("http://project-first.eu/FIRSTOntology_INDICATOR.owl#Reputation_Indicator_Corporate_Governance", "Reputation_Indicator_Corporate_Governance", "cg"),
	CORPORATE_GOVERNANCE("http://project-first.eu/FIRSTOntology_INDICATOR.owl#Reputation_Indicator_Social_Responsibility", "Reputation_Indicator_Social_Responsibility", "sr");
	
	
	private final String URI;  
	private final String name; 
	private final String shortName;
	
	    
	ReputationTopic(String URI, String name, String shortName) {
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
	
	public ReputationTopic getReputationURIByName() {
		return null;
		
	}

}
