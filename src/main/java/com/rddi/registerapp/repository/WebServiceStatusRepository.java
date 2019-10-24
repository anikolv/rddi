package com.rddi.registerapp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.rddi.registerapp.model.WebService;
import com.rddi.registerapp.model.WebServiceStatus;

@Repository
public interface WebServiceStatusRepository extends JpaRepository<WebServiceStatus, Long>, QuerydslPredicateExecutor<WebServiceStatus> {

	WebServiceStatus findTop1ByWebService(WebService webService);

	List<WebServiceStatus> findByWebServiceAndCheckedAtAfter(WebService webService, Date dateAfter);

}
