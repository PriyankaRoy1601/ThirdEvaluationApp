package com.nissan.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="users_details")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	private String userName;
	private String password;
	private boolean isActive;

	private Integer rollId;
	@ManyToOne
	@JoinColumn(name="rollId",insertable=false,updatable=false)
	private Role role;
	
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}



	public User(Integer userId, String userName, String password, boolean isActive, Integer roleId, Role role) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.isActive = isActive;
		this.rollId = roleId;
		this.role = role;
	}



	public Integer getUserId() {
		return userId;
	}



	public void setUserId(Integer userId) {
		this.userId = userId;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public boolean isActive() {
		return isActive;
	}



	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}



	public Integer getRoleId() {
		return rollId;
	}



	public void setRoleId(Integer roleId) {
		this.rollId = roleId;
	}



	public Role getRole() {
		return role;
	}



	public void setRole(Role role) {
		this.role = role;
	}



	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", isActive=" + isActive
				+ ", roleId=" + rollId + ", role=" + role + "]";
	}
	
	
	
	
	

}
