package eu.first.sentify.companypage.drilldown;

public class NewsPhrase {

	String text;
	Integer startNode;
	Integer endNode;
	Integer sentimentScore;
	String phraseType;
	Long phraseTypeId;
	Long id;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getStartNode() {
		return startNode;
	}
	public void setStartNode(Integer startNode) {
		this.startNode = startNode;
	}
	public Integer getEndNode() {
		return endNode;
	}
	public void setEndNode(Integer endNode) {
		this.endNode = endNode;
	}
	public Integer getSentimentScore() {
		return sentimentScore;
	}
	public void setSentimentScore(Integer i) {
		this.sentimentScore = i;
	}
	public String getPhraseType() {
		return phraseType;
	}
	public void setPhraseType(String phraseType) {
		this.phraseType = phraseType;
	}
	public Long getPhraseTypeId() {
		return phraseTypeId;
	}
	public void setPhraseTypeId(Long phraseTypeId) {
		this.phraseTypeId = phraseTypeId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
