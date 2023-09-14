package com.nissan.rest;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nissan.common.APIResponse;
import com.nissan.model.Customer;
import com.nissan.service.ICustomerService;
import com.nissan.util.JwtUtil;

@RestController
@RequestMapping("/api")
public class CustomerController {
	
	@Autowired
	private ICustomerService customerService;

	@Autowired
	private APIResponse apiResponse;
	
	@Autowired
	private JwtUtil jwtUtil;

	//Deposit money
	@PutMapping("/customer/deposit/{accno}/{money}")
	public ResponseEntity<APIResponse> Deposit(@PathVariable long accno,@PathVariable double money,@RequestHeader(value="authorization",defaultValue="")String auth) throws AccessDeniedException {
		jwtUtil.verifyC(auth);
		if(money>50000) {
			apiResponse.setData("ENTER PAN CARD IN API");
			apiResponse.setStatus(200);
			apiResponse.setError(null);
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}
		else {
			customerService.deposit(accno, money);
			apiResponse.setData("DEPOSITED SUCCESSFULLY");
			apiResponse.setStatus(200);
			apiResponse.setError(null);
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
			
		}
	}
	
	//Deposit money greater than 50000
		@PutMapping("/customer/deposit/{accno}/{pan}/{money}")
		public ResponseEntity<APIResponse> Deposit(@PathVariable long accno,@PathVariable double money,@PathVariable long pan,@RequestHeader(value="authorization",defaultValue="")String auth) throws AccessDeniedException {
			jwtUtil.verifyC(auth);
			customerService.deposit(accno, money);
			apiResponse.setData("DEPOSITED SUCCESSFULLY");
			apiResponse.setStatus(200);
			apiResponse.setError(null);
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
				
			
		}
	
	
	//Withdraw money
	@PutMapping("/customer/withdraw/{accno}/{money}")
	public ResponseEntity<APIResponse> Withdraw(@PathVariable long accno,@PathVariable double money,@RequestHeader(value="authorization",defaultValue="")String auth) throws AccessDeniedException {
		jwtUtil.verifyC(auth);
		int flag=customerService.withdraw(accno,money);
		if(flag==1) {
			apiResponse.setData("WITHDREW SUCCESSFULLY");
			apiResponse.setStatus(200);
			apiResponse.setError(null);
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}
		else {
			apiResponse.setData("CANNOT WITHDRAW...INSUFFICIENT FUNDS");
			apiResponse.setStatus(200);
			apiResponse.setError(null);
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}
	}
	
	//Show Balance
	@GetMapping("customer/{accno}")
	public double getCustomer(@PathVariable long accno,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) throws AccessDeniedException {
		jwtUtil.verifyA(auth);
		return customerService.balanceDisplay(accno);
	}

	
	//transferring money
	@PutMapping("/customer/{accno1}/{accno2}/{amount}")
	public ResponseEntity<APIResponse> transferAmount(@PathVariable long accno1,@PathVariable long accno2,@PathVariable double amount,@RequestHeader(value="authorization",defaultValue="")String auth) throws AccessDeniedException{
		jwtUtil.verifyC(auth);
		customerService.transfer(accno1,accno2,amount);
		apiResponse.setData("TRANSFERRED SUCCESSFULLY");
		apiResponse.setStatus(200);
		apiResponse.setError(null);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

}

