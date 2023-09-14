package com.nissan.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.InvalidNameException;

import org.springframework.stereotype.Component;

@Component
public class Validation {

	// name validation
	public boolean isNameValid(String name) {

		boolean bool=false; 
		try {
			Pattern namePattern = Pattern.compile("[^A-Za-z]");
			Matcher nameMatcher = namePattern.matcher(name);
			if ((nameMatcher.find())) {
				throw new InvalidNameException("Hey Invalid name");
			}else if(!(nameMatcher.find())&&name.length()>30) {
				throw new InvalidNameException("Hey Invalid name");
			}
			else {
				bool = true;
			}
		} catch (InvalidNameException e) {
			e.getMessage();
		}
		return bool;
	} 

	//Account number validation
	public boolean ValidNumber(String acno) {

		boolean bool = false;
		try {
			Pattern patternBalance = Pattern.compile("[^0-9.-]");
			Matcher matcherBalance = patternBalance.matcher(acno);
			if (matcherBalance.find()) {
				throw new InvalidNameException("Invalid no.");
			} else if(!(matcherBalance.find())&&acno.length()!=9) {
				throw new InvalidNameException("Invalid no.");
			}
			else
				bool = true;

		} catch (InvalidNameException e) {
			e.getMessage();
		}
		return bool;
	}

	//Mobile number validation
	public boolean MobileValidation(String mbno) {

		boolean bool = false;
		try {

			// creating pattern using regular expression
			Pattern patternBalance = Pattern.compile("[^0-9.-]");
			Matcher matcherBalance = patternBalance.matcher(mbno);
			if (matcherBalance.find()) {
				throw new InvalidNameException("Invalid mobile no.");
			}else if(mbno.length()!=10)
				throw new InvalidNameException("Invalid mobile no");
			else
				bool = true;
		} catch (InvalidNameException e) {
			System.out.println("Invalid mobile number");
		}

		return bool;

	}
	
	//Balance validation
	public boolean ValidBalance(String balance) {

		boolean bool = false;
		try {
			Pattern patternBalance = Pattern.compile("[^0-9.-]");
			Matcher matcherBalance = patternBalance.matcher(balance);
			if (matcherBalance.find()) {
				throw new InvalidNameException("Invalid no.");
			} 
			else
				bool = true;

		} catch (InvalidNameException e) {
			e.getMessage();
		}
		return bool;
	}

	
	

}
