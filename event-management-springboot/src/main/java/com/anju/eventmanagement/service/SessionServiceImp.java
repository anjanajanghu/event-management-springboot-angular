package com.anju.eventmanagement.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.anju.eventmanagement.model.Session;
import com.anju.eventmanagement.repository.SessionRepository;

@Service
public class SessionServiceImp implements SessionService{

	private final SessionRepository sessioRepository;
	
	public SessionServiceImp(SessionRepository sessioRepository) {
		super();
		this.sessioRepository = sessioRepository;
	}

	@Override
	public Set<Session> findAll() {
		Set<Session> sessions = new HashSet<Session>();
		sessioRepository.findAll().forEach(sessions::add);
		return sessions;
	}

	@Override
	public Session findById(Long id) {
		return sessioRepository.findById(id).orElse(null);
	}

	@Override
	public Session save(Session object) {
		return sessioRepository.save(object);
	}

	@Override
	public void delete(Session object) {
		sessioRepository.delete(object);
		
	}

	@Override
	public void deleteById(Long id) {
		sessioRepository.deleteById(id);
		
	}

}
