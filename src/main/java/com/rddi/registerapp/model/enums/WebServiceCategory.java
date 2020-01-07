package com.rddi.registerapp.model.enums;

public enum WebServiceCategory {
	
	ADVERTISING("Advertising"), 
	COMMUNICATIONS("Communications"), 
	CREATIVE("Creative"), 
	ENTERTAIMENT("Entertaiment"), 
	FASHON("Fashion"), 
	HOSPITALITY("Hospitality"), 
	IT("IT"), 
	MANUFACTURING("Manufacturing"), 
	MEDIA("Media"), 
	ROBOTICS("Robotics"), 
	TELECOM("Telecom"), 
	AGRICULTURE("Agriculture"), 
	EDUCATION("Education"), 
	FARMING("Farming"), 
	FINANCE("Finance"), 
	INFRASTRUCTURE("Infrastructure");
	
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
