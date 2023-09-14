package com.nissan.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nissan.model.Customer;

@Repository
public interface IAdminRepository extends CrudRepository<Customer, Long> {

	@Modifying
	@Transactional
	@Query("UPDATE Customer c SET c.isActive = false WHERE c.accountNo = :accno")
	void updateIsActive(@Param("accno") long accno);

}
