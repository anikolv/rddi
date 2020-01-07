package com.rddi.registerapp.model.enums;

public enum ServiceProviderType {
	
	CORPORATION("Corporation"),
	JOIN_STOCK_COMPANY("Join stock company"), 
	REAL_ESTATE_INVESTMENT_TRUST("Real estate investment trust"), 
	SOLE_PROPRIETORSHIP("Sole proprietorship"),
	LTD("Ltd"),
	LIMITED_PARTNERSHIP("Limited partnership"),
	GENERAL_PARTNERSHIP("General partnership"),
	CHARITED_COMPANY("Charited company"),
	STATUTORY_COMPANY("Statutory company"),
	HOLDING_COMPANY("Holding company"),
	SUBSIDIARY_COMPANY("Subsidiary company"),
	ONE_MAN_COMPANY("One man company"),
	GOVERNMENTAL_ORGANIZATION("Governmental organization"),
	NON_GOVERNMENTAL_ORGANIZATION("Non-governmental organization");
	
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
