package eu.first.sentify.reputationtopics;

import java.util.HashMap;


public class ReputationTopicSearch {

	// name -> reputation object
	HashMap<String, ReputationTopic> topicMap = new HashMap<String, ReputationTopic>();
	// shortName -> reputation object
	HashMap<String, ReputationTopic> shortNameMap = new HashMap<String, ReputationTopic>();
	
	private ReputationTopicSearch() {
		for (ReputationTopic a : ReputationTopic.values()) {
			topicMap.put(a.getName(), a);
			topicMap.put(a.getName().replace("_", " "), a);
			topicMap.put(a.getName().replace("_", " ").toLowerCase(), a);

			shortNameMap.put(a.getShortName(), a);
		}
	}
	
    /**
    * SingletonHolder is loaded on the first execution of Singleton.getInstance() 
    * or the first access to SingletonHolder.INSTANCE, not before.
    */
    private static class SingletonHolder { 
            public static final ReputationTopicSearch INSTANCE = new ReputationTopicSearch();
    }

    public static ReputationTopicSearch getInstance() {
            return SingletonHolder.INSTANCE;
    }
    
    public String getReputationTopicURIByName(String name) {
    	if (topicMap.get(name) != null) {
    		return topicMap.get(name).getURI();
    	} else {
    		return null;
    	}
    }
    
    public String getReputationTopicURIByShortName(String name) {
    	if (shortNameMap.get(name) != null) {
    		return shortNameMap.get(name).getURI();
    	} else {
    		return null;
    	}
    }
    
    public ReputationTopic getReputationTopicByName(String name) {
    	if (shortNameMap.get(name) != null) {
    		return shortNameMap.get(name);
    	} else {
    		return null;
    	}
    }
    
    
}
