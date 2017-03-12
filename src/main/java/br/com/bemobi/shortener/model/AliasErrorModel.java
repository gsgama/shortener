package br.com.bemobi.shortener.model;

public class AliasErrorModel extends ErrorModel {
	private String alias;
	
	public AliasErrorModel(String err_code, String description, String alias) {
		super(err_code, description);
		this.alias = alias;
	}
	
	public String getAlias() {
		return alias;
	}
}
