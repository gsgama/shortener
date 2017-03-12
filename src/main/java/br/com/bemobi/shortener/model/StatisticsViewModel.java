package br.com.bemobi.shortener.model;

public class StatisticsViewModel {
	private String time_taken;

	public StatisticsViewModel(String time_taken) {
		super();
		this.time_taken = time_taken;
	}
	
	public String getTime_taken() {
		return time_taken;
	}	
}
