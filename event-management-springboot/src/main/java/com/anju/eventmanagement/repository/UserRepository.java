package com.anju.eventmanagement.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anju.eventmanagement.model.User;
@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	@Query("FROM User WHERE username=:username and password=:password")
    public User getUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
	
	public User getUserByUsername(String  username);

}
