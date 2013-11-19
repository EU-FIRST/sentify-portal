package eu.first.sentify.search.model;

import java.util.ArrayList;

public class SentimentObject {
	String name;
	String URI;
	String URIFragment;
	ArrayList<String> symbols = new ArrayList<String>();
	String symbol;
	String mapping;
	String wikipedia;
	String type;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getURIFragment() {
		return URIFragment;
	}
	public void setURIFragment(String uRIFragment) {
		URIFragment = uRIFragment;
	}
	public String getWikipedia() {
		return wikipedia;
	}
	public void setWikipedia(String wikipedia) {
		this.wikipedia = wikipedia;
	}
	public String getMapping() {
		return mapping;
	}
	public void setMapping(String mapping) {
		this.mapping = mapping;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getURI() {
		return URI;
	}
	public void setURI(String uRI) {
		URI = uRI;
	}
	public ArrayList<String> getSymbols() {
		return symbols;
	}
	public void setSymbols(ArrayList<String> symbols) {
		this.symbols = symbols;
	}
	public void addSymbol(String symbol) {
		if (this.symbols != null) {
			this.symbols.add(symbol);
		}
	}

}
