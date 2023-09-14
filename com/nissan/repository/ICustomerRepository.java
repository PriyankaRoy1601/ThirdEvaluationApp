package com.nissan.repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nissan.model.Customer;

@Repository
public interface ICustomerRepository extends CrudRepository<Customer, Long> {

	
	@Modifying
	@Transactional
	@Query("UPDATE Customer c SET c.balance = c.balance + :money WHERE c.accountNo = :accno")
	void deposit(@Param("accno") long accno, @Param("money") double money);
	
	
	@Modifying
	@Transactional
	@Query("UPDATE Customer c SET c.balance = c.balance - :money  WHERE c.accountNo = :accno")
	void withdraw(@Param("accno") long accno, @Param("money") double money);

    @Query("SELECT c.balance FROM Customer c WHERE c.accountNo=:accno")
	double balance(@Param("accno")long accno);

    @Query("SELECT c.minBalance FROM Customer c WHERE c.accountNo=:accno")
	double availBalance(@Param("accno")long accno);



}
