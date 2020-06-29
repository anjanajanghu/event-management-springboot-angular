package com.anju.eventmanagement.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.anju.eventmanagement.exception.RecordNotFoundException;
import com.anju.eventmanagement.model.User;
import com.anju.eventmanagement.service.UserService;
 
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
    UserService userservice;
 
    @GetMapping
    public ResponseEntity<Set<User>> getAllUsers() {
    	Set<User> users = new HashSet<User>();
    	users = userservice.findAll();
 
        return new ResponseEntity<Set<User>>(users, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
    	User user = userservice.findById(id);
 
        return new ResponseEntity<User>(user, new HttpHeaders(), HttpStatus.OK);
    }
  
	@PostMapping
	public  ResponseEntity<User> addUser(@Validated  @RequestBody User user) throws RecordNotFoundException {
		User tempUser = userservice.getUserByUsername(user.getUsername());
	     if (tempUser != null) {
	           throw new RuntimeException("user with username: " + user.getUsername() + " already exists");
	     }
	     User updated = userservice.save(user);
	     return new ResponseEntity<User>(updated, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/update")
	public  ResponseEntity<User> updateUser(@Validated  @RequestBody User user) throws RecordNotFoundException {
		User updated = userservice.save(user);
	   return new ResponseEntity<User>(updated, new HttpHeaders(), HttpStatus.OK);
	}
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteUserById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        userservice.deleteById(id);
        return HttpStatus.FORBIDDEN;
    }
    
    @RequestMapping(method = RequestMethod.POST, path = "/login/{username}/{password}")
    public User loginUser(@PathVariable("username") String username, @PathVariable("password") String password) {
       return userservice.getUserByUsernameAndPassword(username, password);
    }
 
}
