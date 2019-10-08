package com.rddi.registerapp.model.enums;

public enum ServiceProviderType {
	
	BUSINESS_ORGANIZATION("Business organization"), 
	SOFTWARE_FOUNDATION("Software foundation"),
	PRIVATE_SERVICE_PROVIDER("Private service provider"),
	OTHER("Other");
	
	private String name;

	private ServiceProviderType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
