package com.rddi.registerapp.model.enums;

public enum WebServiceType {
	
	SANDBOX("Sandbox"), 
	OFF_THE_SHELF("Off the shelf");
	
	private String name;

	private WebServiceType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
