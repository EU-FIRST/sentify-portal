package test.sentify.search;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.queryparser.classic.ParseException;

import eu.first.sentify.search.model.SentimentObject;
import eu.first.sentify.searchsentimentobjects.CompanySearch;

public class SearchTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CompanySearch cs = CompanySearch.getInstance();
		
		try {
			ArrayList<SentimentObject> res = cs.searchCompanyWildcards("micro");
			for (SentimentObject so : res) {
				System.out.println(so.getName() + " " + so.getType() + " " + so.getSymbol());
			}
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
