package com.rddi.registerapp.model.enums;

public enum WebServiceCategory {
	
	FINTECH("Finances"), 
	HEALTHCARE("Healthcare and pharmacy"),
	WEATHER_AND_FORECASTS("Weather and forecasts"),
	TELCO("Telecommunications"),
	TRANSPORT("Transport"),
	EDUCATION("Education"),
	UTILITIES("Utitilies"),
	IT("Information technologies"),
	OTHER("Other");
	
	private String name;

	private WebServiceCategory(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
