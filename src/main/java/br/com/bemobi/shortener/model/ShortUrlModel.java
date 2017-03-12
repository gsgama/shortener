package br.com.bemobi.shortener.model;

public class ShortUrlModel {
	private String url;
	private String alias;
	private StatisticsModel statistics;
	
	public ShortUrlModel(String url, String alias, String time_taken) {
		super();
		this.url = url;
		this.alias = alias;
		this.statistics = new StatisticsModel(time_taken);
	}

	public String getUrl() {
		return url;
	}

	public String getAlias() {
		return alias;
	}

	public StatisticsModel getStatistics() {
		return statistics;
	}
}
