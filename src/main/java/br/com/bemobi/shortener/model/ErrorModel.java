package br.com.bemobi.shortener.model;

public class ErrorModel {
	private String err_code;
	private String description;

	public ErrorModel(String err_code, String description) {
		super();
		this.err_code = err_code;
		this.description = description;
	}

	public String getErr_code() {
		return err_code;
	}

	public String getDescription() {
		return description;
	}

}
