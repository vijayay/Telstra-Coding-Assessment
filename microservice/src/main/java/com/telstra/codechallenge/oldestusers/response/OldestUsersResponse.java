package com.telstra.codechallenge.oldestusers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public class OldestUsersResponse {

	 private Items[] items;
	 
	  public OldestUsersResponse(Items[] items) {
		super();
		this.items = items;
	}

	public OldestUsersResponse() {
		// TODO Auto-generated constructor stub
	}

	public Items[] getItems() {
		return items;
	}

	public void setItems(Items[] items) {
		this.items = items;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	  @Data
	  public class Items {

	    private Integer id;
	    private String login;
	    private String html_url;	    
		
		public Items(Integer id, String login, String html_url) {
			super();
			this.id = id;
			this.login = login;
			this.html_url = html_url;
		}
		
	  }
}
