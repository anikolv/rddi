package com.rddi.registerapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rddi.registerapp.model.WebService;
import com.rddi.registerapp.model.WebServiceRating;

@Repository
public interface WebServiceRatingRepository extends JpaRepository<WebServiceRating, Long> {
	
	List<WebServiceRating> findAllByWebService(WebService webService);

}
