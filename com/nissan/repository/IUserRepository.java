package com.nissan.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nissan.model.User;

@Repository
public interface IUserRepository extends CrudRepository<User,Integer>{
	
	//custom method
	@Query("from User WHERE userName like %?1% AND password like %?2% AND rollId=?3")
	public User findUserByNameAndPassword(String userName,String password,int roleId);

}
