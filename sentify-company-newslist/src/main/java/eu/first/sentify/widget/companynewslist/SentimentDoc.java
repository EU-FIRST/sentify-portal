package eu.first.sentify.widget.companynewslist;

import java.util.Date;

public class SentimentDoc {
	String title;
	String linternalLink;
	String url;
	Date date;
	String source;
	long id;
	double score;
	
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getInternalLink() {
		return linternalLink;
	}
	public void setInternalLink(String link) {
		this.linternalLink = link;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}

	
	
}
