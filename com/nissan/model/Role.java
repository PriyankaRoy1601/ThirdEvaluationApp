package com.nissan.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int rollId;
	private String roleName;
	
	
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Role(int rollId, String roleName) {
		super();
		this.rollId = rollId;
		this.roleName = roleName;
		
	}


	public int getRollId() {
		return rollId;
	}


	public void setRollId(int rollId) {
		this.rollId = rollId;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	

}
