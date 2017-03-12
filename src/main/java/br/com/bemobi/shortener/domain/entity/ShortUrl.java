package br.com.bemobi.shortener.domain.entity;

import org.springframework.data.annotation.Id;

public class ShortUrl {

	@Id
	private String id;
	private String url;
	private String alias;
	private long accesses;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public long getAccesses() {
		return accesses;
	}
	
	public void setAccesses(Long accesses) {
		this.accesses = accesses;
	}
}
