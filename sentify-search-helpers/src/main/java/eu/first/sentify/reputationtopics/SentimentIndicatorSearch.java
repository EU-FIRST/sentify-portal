package eu.first.sentify.reputationtopics;

import java.util.HashMap;


public class SentimentIndicatorSearch {

	// name -> indicator
	HashMap<String, SentimentIndicator> topicMap = new HashMap<String, SentimentIndicator>();
	// shortName -> indicator
	HashMap<String, SentimentIndicator> shortNameMap = new HashMap<String, SentimentIndicator>();
	
	private SentimentIndicatorSearch() {
		for (SentimentIndicator a : SentimentIndicator.values()) {
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
            public static final SentimentIndicatorSearch INSTANCE = new SentimentIndicatorSearch();
    }

    public static SentimentIndicatorSearch getInstance() {
            return SingletonHolder.INSTANCE;
    }
    
    public String getSentimentIndicatorURIByName(String name) {
    	if (topicMap.get(name) != null) {
    		return topicMap.get(name).getURI();
    	} else {
    		return null;
    	}
    }
    
    public String getSentimentIndicatorURIByShortName(String name) {
    	if (shortNameMap.get(name) != null) {
    		return shortNameMap.get(name).getURI();
    	} else {
    		return null;
    	}
    }
    
    public SentimentIndicator getSentimentIndicatorByName(String name) {
    	if (shortNameMap.get(name) != null) {
    		return shortNameMap.get(name);
    	} else {
    		return null;
    	}
    }
    
    
}
