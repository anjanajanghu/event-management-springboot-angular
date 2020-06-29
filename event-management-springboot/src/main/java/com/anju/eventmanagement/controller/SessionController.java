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
import org.springframework.web.bind.annotation.RestController;

import com.anju.eventmanagement.exception.RecordNotFoundException;
import com.anju.eventmanagement.model.Event;
import com.anju.eventmanagement.model.Session;
import com.anju.eventmanagement.service.EventService;
import com.anju.eventmanagement.service.SessionService;
 
@CrossOrigin(origins = {"http://localhost:4200"}) 
@RestController
@RequestMapping("/sessions")
public class SessionController {
	@Autowired
    SessionService sessionservice;
	
	@Autowired
    EventService eventservice;
	
    @GetMapping
    public ResponseEntity<Set<Session>> getAllSessions() {
    	Set<Session> sessions = new HashSet<Session>();
    	sessions = sessionservice.findAll();
 
        return new ResponseEntity<Set<Session>>(sessions, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Session> getSessionById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
    	Session session = sessionservice.findById(id);
 
        return new ResponseEntity<Session>(session, new HttpHeaders(), HttpStatus.OK);
    }
 
   @PostMapping("/{id}")
    public ResponseEntity<Session> createOrUpdateSession(@Validated @RequestBody Session session, @PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
	    Event event = eventservice.findById(id);
	    session.setEvent(event);
    	Session updated = sessionservice.save(session);
        return new ResponseEntity<Session>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteSessionById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        sessionservice.deleteById(id);
        return HttpStatus.FORBIDDEN;
    }
 
}
