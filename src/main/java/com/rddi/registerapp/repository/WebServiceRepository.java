package com.rddi.registerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rddi.registerapp.model.WebService;

@Repository
public interface WebServiceRepository extends JpaRepository<WebService, Long>{

}
