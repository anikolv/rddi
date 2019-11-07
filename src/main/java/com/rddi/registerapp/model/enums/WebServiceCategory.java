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
	DEDICATED_SOFTWARE("Dedicated software"),
	GOVERNMENTAL_SERVICES("Governmental services");
	
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
