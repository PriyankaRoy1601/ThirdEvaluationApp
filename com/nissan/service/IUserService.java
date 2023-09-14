package com.nissan.service;

import com.nissan.common.APIResponse;

public interface IUserService {
	
	public APIResponse findUserByNameAndPassword(String userName,String password,int roleId);

}
