package com.rddi.registerapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rddi.registerapp.model.WebService;
import com.rddi.registerapp.model.WebServiceComment;

@Repository
public interface WebServiceCommentRepository extends JpaRepository<WebServiceComment, Long> {
	
	List<WebServiceComment> findAllByWebServiceOrderByIdDesc(WebService webService);

}
