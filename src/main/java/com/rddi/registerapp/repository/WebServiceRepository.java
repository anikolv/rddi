package com.rddi.registerapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.rddi.registerapp.model.WebService;

@Repository
public interface WebServiceRepository extends JpaRepository<WebService, Long>, QuerydslPredicateExecutor<WebService> {
	
	@Query("SELECT ws " +
		   "FROM WebService ws " +
		   "LEFT JOIN FETCH ws.httpStatuses s")
	List<WebService> findAllWithFetchedStatuses();
	
	@Query("SELECT ws " +
		   "FROM WebService ws " +
		   "LEFT JOIN FETCH ws.httpStatuses s")
	Boolean getWebServiceHttpStatus();

}
