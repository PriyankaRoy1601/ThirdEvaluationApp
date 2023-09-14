package com.nissan.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nissan.common.APIResponse;
import com.nissan.model.User;
import com.nissan.repository.IUserRepository;
import com.nissan.util.JwtUtil;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserRepository userRepo;
	@Autowired
	private APIResponse apiresponse;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	@Override
	public APIResponse findUserByNameAndPassword(String userName, String password,int roleId) {
		// TODO Auto-generated method stub
		//verify user exists
		User user=userRepo.findUserByNameAndPassword(userName, password,roleId);
		if(user==null) {
			apiresponse.setData("INVALID CREDENTIALS!!");
			return apiresponse;
		}
		
		if(roleId==1) {
		//credentials are correct
			String token = jwtUtil.generateJwtAdmin(user);
			
		//storing more details and token
			Map<String,Object>data=new HashMap<String,Object>();
			data.put("ACCESSTOKEN",token);
			data.put("role",user.getRoleId());
			data.put("Username",user.getUserName());
			
			apiresponse.setStatus(200);
			apiresponse.setData(data);
		}
		
		else if(roleId==2) {
			String token = jwtUtil.generateJwtCustomer(user);
			
			//storing more details and token
				Map<String,Object>data=new HashMap<String,Object>();
				data.put("ACCESSTOKEN",token);
				data.put("role",user.getRoleId());
				data.put("Username",user.getUserName());
				
				apiresponse.setStatus(200);
				apiresponse.setData(data);
			
		}
			return apiresponse;

}
}
