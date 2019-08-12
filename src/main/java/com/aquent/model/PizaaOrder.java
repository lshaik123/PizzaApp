package com.aquent.model;

import java.sql.Date;

public class PizaaOrder {
	
	  private int id;
	  private String name;
	  private String type;
	  private long date;
	  
	  
	  
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	
	

}
