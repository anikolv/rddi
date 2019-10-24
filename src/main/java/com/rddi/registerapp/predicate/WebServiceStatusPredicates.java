package com.rddi.registerapp.predicate;

import java.util.function.Predicate;

import com.rddi.registerapp.model.WebServiceStatus;

public class WebServiceStatusPredicates {
	
	public static Predicate<WebServiceStatus> notAvailable() {
		return ws -> !ws.getAvailable();
	}
	
	public static Predicate<WebServiceStatus> available() {
		return ws -> ws.getAvailable();
	}

}
