package com.nissan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nissan.common.Validation;
import com.nissan.model.Customer;
import com.nissan.repository.IAdminRepository;

@Service
public class AdminService implements IAdminService {
	
	@Autowired
	private IAdminRepository adminRepo;
	
	@Autowired
	private Validation validation;

	//List Customer
	@Override
	public List<Customer> getCustomer() {
		// TODO Auto-generated method stub
		return (List<Customer>)adminRepo.findAll();
	}

	//Search by account no.
	@Override
	public Customer getCustomer(long accno) {
		// TODO Auto-generated method stub
		return adminRepo.findById(accno).orElseThrow(()-> new RuntimeException("Customer not found for account no."+accno));
	}

	//Save new values and have proper validations
	@Override
	public Object saveCustomer(Customer customer) {
		if(validation.isNameValid(customer.getName())&&validation.MobileValidation(String.valueOf(customer.getMobNo()))){
			return adminRepo.save(customer);}
		
		return null;
	}

	//Changing isActive session
	@Override
	public void deleteCustomer(long accno) {
		// TODO Auto-generated method stub
		adminRepo.updateIsActive(accno);
		
	}

}
