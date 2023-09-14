package com.nissan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nissan.common.Validation;
import com.nissan.model.Customer;
import com.nissan.repository.ICustomerRepository;

@Service
public class CustomerService implements ICustomerService {

	@Autowired
	private ICustomerRepository customerRepo;

	@Autowired
	private Validation validation;

	Customer customer1=new Customer();
	Customer customer2=new Customer();

	@Override
	public List<Customer> getCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomer(long accno) {
		// TODO Auto-generated method stub
		if (validation.ValidNumber(String.valueOf(accno))) {
			return customerRepo.findById(accno)
					.orElseThrow(() -> new RuntimeException("Customer not found for account no." + accno));
		}
		return null;
	}

	@Override
	public List<Customer> deposit(long accno, double money) {
		// TODO Auto-generated method stub
		if (validation.ValidBalance(String.valueOf(money)) && validation.ValidNumber(String.valueOf(accno))) {
			customerRepo.deposit(accno, money);
		}
		return null;
	}

	@Override
	public int withdraw(long accno, double money) {
		// TODO Auto-generated method stub
		if (validation.ValidBalance(String.valueOf(money)) && validation.ValidNumber(String.valueOf(accno))) {
			double balance=customerRepo.balance(accno);
			double availBalance=customerRepo.availBalance(accno);
			if(balance-availBalance>money)
			customerRepo.withdraw(accno, money);
			return 1;
			}
		
		
		
		return 0;
	}

	//Transferring money
	@Override
	public int transfer(long accno1, long accno2, double amount) {
		// TODO Auto-generated method stub
		if (validation.ValidNumber(String.valueOf(accno1)) && validation.ValidNumber(String.valueOf(accno2))&&validation.ValidBalance(String.valueOf(amount))) {
			double balance=customerRepo.balance(accno1);
			double availBalance=customerRepo.availBalance(accno1);
			if (balance-availBalance>amount) {
				customerRepo.withdraw(accno1, amount);
				customerRepo.deposit(accno2, amount);
				return 1;
			}
		}
		
		
		return 0;
	}

	@Override
	public double balanceDisplay(long accno) {
		// TODO Auto-generated method stub
		return customerRepo.balance(accno);
		
	}


}
