package com.rddi.registerapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.rddi.registerapp.model.WebService;
import com.rddi.registerapp.repository.WebServiceRepository;

@Service
public class WebServiceSanityCheckScheduler {
	
	@Autowired
	private WebServiceRepository webServiceRepository;
	
	@Autowired
	private WebServiceManagement webServiceManagement;
	
	@Scheduled(cron = "0 0 12 * * *")
	public void performSanityCheck() {
	    System.out.println("[SANITY CHECK SCHEDULER START]");
	    
		List<WebService> webServices = webServiceRepository.findAllWithFetchedStatuses();
		webServices.forEach(webService -> {
			try {
				webServiceManagement.checkWebServiceAvailability(webService);
			} catch (Exception e) {
				System.out.println("[SANITY CHECK ERROR]" + e.getMessage());
			}
		});
		 System.out.println("[SANITY CHECK SCHEDULER END]");
	}
}
