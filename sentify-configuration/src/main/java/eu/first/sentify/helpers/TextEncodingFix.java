package eu.first.sentify.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TextEncodingFix {

	//static ArrayList<String>  stopwords = new ArrayList<String>(Arrays.asList("SA", "S.A.", "PLC", "N.V.", "S.p.A.", "LTD.", "Inc.", "AB", "AG", "CORP", "Corp.", "NPV", "S.C.a.R.L", "SCA", "S. De R.L. de C.V.", "CO", "Sc"));
	static Map<String,String> map = new HashMap<String, String>(){{
	    put("â€¦", "…");
	    put("â€“", "–");
	    put("â€™", "’");
	    put("â€œ", "“");
	    put("â€?", "”");
	    put("â€˜", "‘");
	    put("â'¬", "€");
	    
	}};
	
	public static String fixText(String input) {
		for (String s : map.keySet()) {
			input = input.replace(s, map.get(s));			
		}
		
		return input;
	}
	
	
	public static void main(String[] args) {
		String test1 = "â€œConsidering the size and general health of our economy when compared to the difficult recessions other countries in this report have experienced, it is clear Canada is not doing enough and needs to invest more in our children,â€? Morley said.";
		String test2 = "â€˜They can't afford to disappoint customers.â€™"; 
		System.out.println(TextEncodingFix.fixText(test1));
		System.out.println(TextEncodingFix.fixText(test2));
		
	}
}
