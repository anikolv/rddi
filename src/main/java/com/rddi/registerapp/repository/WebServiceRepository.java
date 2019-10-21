package com.rddi.registerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.rddi.registerapp.model.WebService;

@Repository
public interface WebServiceRepository extends JpaRepository<WebService, Long>, QuerydslPredicateExecutor<WebService> {
	
//	@Query("SELECT ws " +
//		   "FROM WebService ws " +
//		   "JOIN ws.serviceProdiver sp " +
//		   "WHERE " +
//		   		  "(" +
//		   		   "ws.name LIKE %:searchTerm% " +
//				   "OR ws.description LIKE %:searchTerm% " +
//				   "OR sp.name LIKE %:searchTerm% " +
//				   "OR sp.description LIKE %:searchTerm%" +
//				   ") " +
//		   "AND ws.category = :apiCategory " + 
//		   "AND ws.type = :apiType " +
//		   "AND sp.type = :serviceProviderType"
//			)
//	List<WebService> search(@Param("searchTerm") String searchTerm);

}
