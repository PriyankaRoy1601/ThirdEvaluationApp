package com.nissan.service;

import java.util.List;

import com.nissan.model.Customer;

public interface IAdminService {

	List<Customer> getCustomer();

	Customer getCustomer(long accno);

	Object saveCustomer(Customer customer);

	void deleteCustomer(long accno);

}
