package com.rddi.registerapp.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.rddi.registerapp.model.WebService;
import com.rddi.registerapp.model.WebServiceStatus;
import com.rddi.registerapp.repository.WebServiceRepository;

@Service
public class WebServiceSanityCheckScheduler {
	
	@Autowired
	private WebServiceRepository webServiceRepository;
	
	@Scheduled(cron = "0 0 12 * * *")
	public void performSanityCheck() {
	    System.out.println("[SANITY CHECK SCHEDULER START]");
	    
		List<WebService> webServices = webServiceRepository.findAllWithFetchedStatuses();
		webServices.forEach(webService -> {
			try {
				URL url = new URL(webService.getOpenApiContract());
				HttpURLConnection http = (HttpURLConnection)url.openConnection();
				
				WebServiceStatus status = new WebServiceStatus();
				status.setHttpStatusCode(String.valueOf(http.getResponseCode()));
				status.setHttpStatusMessage(http.getResponseMessage());
				status.setAvailable(
						String.valueOf(http.getResponseCode()).equals(String.valueOf(HttpStatus.OK.value())));
				status.setCheckedAt(new Date());
				
				webService.addStatus(status);
				webServiceRepository.save(webService);
			} catch (Exception e) {
				System.out.println("[SANITY CHECK ERROR]" + e.getMessage());
			}
		});
		 System.out.println("[SANITY CHECK SCHEDULER END]");
	}

}
