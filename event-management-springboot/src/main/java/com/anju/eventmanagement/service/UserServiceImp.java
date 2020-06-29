package com.anju.eventmanagement.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.anju.eventmanagement.model.User;
import com.anju.eventmanagement.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {
	
	private final UserRepository userRepository;
	
	public UserServiceImp(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public Set<User> findAll() {
		Set<User> users = new HashSet<User>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	@Override
	public User findById(Long id) {
		//return userRepository.findById(id).orElse(null);
		
		Optional<User> result = userRepository.findById(id);
        User user = null;
        if (result.isPresent()) {
            user = result.get();
        } else {
            throw new RuntimeException("user with id: " + id + " not found");
        }
        return user;
	}

	@Override
	public User save(User object) {
		return userRepository.save(object);
	}

	@Override
	public void delete(User object) {
		userRepository.delete(object);
		
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
		
	}
	
	@Override
    public User getUserByUsernameAndPassword(String username, String password) {
        return userRepository.getUserByUsernameAndPassword(username, password);
    }

	@Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

}
