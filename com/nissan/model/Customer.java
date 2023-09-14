package com.nissan.model;

import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Customer")
public class Customer  {
	// data members
	@Id//primary key
	private long accountNo;
	private long mobNo;
	private String name, accountType, email;
	private double balance, minBalance;
	private int pin;
	private String pan;
	private boolean isActive = true;

	public Customer(long accountNo, long mobNo, String name, String accountType, String email, double balance,
			double minBalance, int pin, String pan, boolean isActive) {
		super();
		this.accountNo = accountNo;
		this.mobNo = mobNo;
		this.name = name;
		this.accountType = accountType;
		this.email = email;
		this.balance = balance;
		this.minBalance = minBalance;
		this.pin = pin;
		this.pan = pan;
		this.isActive = isActive;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Customer() {
		super();
	
	}

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo() {
		Random r = new Random();
		this.accountNo = 100000000 + r.nextInt(900000000);
	}

	public long getMobNo() {
		return mobNo;
	}

	public void setMobNo(long mobNo) {
		this.mobNo = mobNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getMinBalance() {
		return minBalance;
	}

	public void setMinBalance(double minBalance) {
		this.minBalance = minBalance;
	}

	public int getPin() {
		return this.pin;
	}

	public void setPin() {
		Random r = new Random();
		this.pin = 1000 + r.nextInt(9000);
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan=pan;
		
	}

}
