package com.nissan.service;

import java.util.List;

import com.nissan.model.Customer;

public interface ICustomerService {

	List<Customer> getCustomer();

	Customer getCustomer(long accno);

	List<Customer> deposit(long accno, double money);

	int withdraw(long accno, double money);

	int transfer(long accno1, long accno2, double amount);

	double balanceDisplay(long accno);
}
