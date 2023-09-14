package com.nissan.rest;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nissan.common.APIResponse;
import com.nissan.model.Customer;
import com.nissan.service.IAdminService;
import com.nissan.util.JwtUtil;

@RestController
@RequestMapping("/api")
public class AdminController {

	@Autowired
	private IAdminService adminService;

	@Autowired
	private APIResponse apiResponse;

	@Autowired
	private JwtUtil jwtUtil;

	// list the customer
	@GetMapping("/admin")
	public List<Customer> getCustomer(@RequestHeader(value = "authorization", defaultValue = "") String auth)
			throws AccessDeniedException {
		jwtUtil.verifyA(auth);
		return adminService.getCustomer();
	}

	// search by account no.
	@GetMapping("/admin/{accno}")
	public Customer getCustomer(@PathVariable long accno,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) throws AccessDeniedException {
		jwtUtil.verifyA(auth);
		return adminService.getCustomer(accno);
	}

	// add details
	@PostMapping("/admin")
	public ResponseEntity<APIResponse> addCustomer(@RequestBody Customer customer,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) throws AccessDeniedException {
		jwtUtil.verifyA(auth);
		customer.setAccountNo();
		customer.setPin();
		if (adminService.saveCustomer(customer) == null) {
			apiResponse.setData("INVALID DATA");
			apiResponse.setStatus(500);
			apiResponse.setError("INVALID DATA.. CANNOT ENTER");
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}
		apiResponse.setData("Customer ADDED SUCCESSFULLY");
		apiResponse.setStatus(200);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

	}

	// update existing detail
	@PutMapping("/admin/{accno}")
	public void updateCustomer(@RequestBody Customer Customer,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) throws AccessDeniedException {
		jwtUtil.verifyA(auth);
		adminService.saveCustomer(Customer);

	}

	// disable or delete Customer ie isActive is changed to false
	@PutMapping("/admin/delete/{accno}")
	public void deleteCustomer(@PathVariable long accno,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) throws AccessDeniedException {
		jwtUtil.verifyA(auth);
		adminService.deleteCustomer(accno);

	}

}
