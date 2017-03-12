package br.com.bemobi.shortener.model;

public class ShortUrlViewModel {
	private String url;
	private String alias;
	private StatisticsViewModel statistics;
	
	public ShortUrlViewModel(String url, String alias, String time_taken) {
		super();
		this.url = url;
		this.alias = alias;
		this.statistics = new StatisticsViewModel(time_taken);
	}

	public String getUrl() {
		return url;
	}

	public String getAlias() {
		return alias;
	}

	public StatisticsViewModel getStatistics() {
		return statistics;
	}
}
