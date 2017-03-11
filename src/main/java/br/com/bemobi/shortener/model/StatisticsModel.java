package br.com.bemobi.shortener.model;

public class StatisticsModel {
	private String time_taken;

	public StatisticsModel(String time_taken) {
		super();
		this.time_taken = time_taken;
	}
	
	public String getTime_taken() {
		return time_taken;
	}	
}
