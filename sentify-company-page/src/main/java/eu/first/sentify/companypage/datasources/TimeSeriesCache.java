package eu.first.sentify.companypage.datasources;

import java.util.HashMap;

import org.apache.commons.collections.map.MultiKeyMap;

public class TimeSeriesCache {
	
	//hashmap: Company, Start Date, End Date, Topic -> Time series
	MultiKeyMap timeSeriesCacheMap = new MultiKeyMap();
	
	
	//HashMap<String, HashMap<String, String>> cacheMapTS = new HashMap<String, HashMap<String, String>>();
	HashMap<String, String> cacheMapTopics = new HashMap<String, String>();

	
	/**
	 * Method for retrieving time series from cache. It directly takes values from the MultiMap
	 * with the following keys: Sentiment Object, Start Date, End Date, Topic -> Time series
	 * 
	 * In case Topic is not specified it should be set to empty string (""), not NULL.
	 * 
	 * Returns null if no cache entry is found.
	 * 
	 * @param object
	 * @param startDate
	 * @param endDate
	 * @param Topic
	 * @return
	 */
	public String getTimeSeries(String object, String startDate, String endDate, String indicator, String topic) {
		return (String) timeSeriesCacheMap.get(object, startDate, endDate, indicator, topic);
		
	}
	
	/**
	 * 
	 * Method for storing time series.
	 *  Sentiment Object, Start Date, End Date, Topic -> Time series
	 * 
	 * @param object
	 * @param startDate
	 * @param endDate
	 * @param topic
	 * @return
	 */
	public void putTimeSeries(String object, String startDate, String endDate, String indicator, String topic, String ts) {
		timeSeriesCacheMap.put(object, startDate, endDate, indicator,  topic, ts);
		
	}
	
	
	
//	public String getTS(String so, String endDate) {
//		String result = null;
//		if (cacheMapTS.containsKey(so) == true) {
//			HashMap<String, String> tshm = cacheMapTS.get(so);
//			if (tshm.containsKey(endDate) == true) {
//				result = tshm.get(endDate);
//			}
//		} 
//		return result;
//	}
//	
//	public void putTS(String so, String endDate, String timeSerie) {
//		if (cacheMapTS.containsKey(so) == true) {
//			HashMap<String, String> tshm = cacheMapTS.get(so);
//			tshm.put(endDate, timeSerie);
//		} else {
//			HashMap<String, String> tshm = new HashMap<String, String>();
//			tshm.put(endDate, timeSerie);
//			cacheMapTS.put(so, tshm);
//		}
//		
//	}
	
	public String getTopics(String endDate) {
		String result = null;

		if (cacheMapTopics.containsKey(endDate) == true) {
			result = cacheMapTopics.get(endDate);
		}

		return result;
	}
	
	public void putTopics(String endDate, String topics) {
		cacheMapTopics.put(endDate, topics);
	}
	
	
	//test
	public static void main(String[] argv) {
		MultiKeyMap timeSeriesCacheMap = new MultiKeyMap();
		timeSeriesCacheMap.put("Google", "2013-06-01", "2013-06-30", "", "JSON1");
		timeSeriesCacheMap.put("Google", "2013-06-01", "2013-06-30", "Feat1", "JSON2");
		timeSeriesCacheMap.put("Google", "2013-06-01", "2013-06-30", "Feat2", "JSON3");
		timeSeriesCacheMap.put("Google", "2013-05-01", "2013-06-30", "", "JSON4");
		timeSeriesCacheMap.put(null, "2013-05-01", "2013-06-31", "", "JSON5");
		
		
		System.out.println(timeSeriesCacheMap.get("Google", "2013-06-01", "2013-06-30", ""));
		System.out.println(timeSeriesCacheMap.get("Google", "2013-06-01", "2013-06-30", "Feat1"));
		System.out.println(timeSeriesCacheMap.get("Google", "2013-05-01", "2013-06-30", ""));
		System.out.println(timeSeriesCacheMap.get("Google", "2013-05-01", "2013-06-31", ""));
		System.out.println(timeSeriesCacheMap.get("Google", null, "2013-06-31", ""));
	}
	
}
