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
import com.anju.eventmanagement.model.User;
import com.anju.eventmanagement.service.EventService;
import com.anju.eventmanagement.service.UserService;
 
@CrossOrigin(origins = {"http://localhost:4200"}) 
@RestController
@RequestMapping("/events")
public class EventController {
	@Autowired
    EventService eventservice;
	
	@Autowired
	UserService userService;
 
    @GetMapping
    public ResponseEntity<Set<Event>> getAllEvents() {
    	Set<Event> events = new HashSet<Event>();
    	events = eventservice.findAll();
 
        return new ResponseEntity<Set<Event>>(events, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
    	Event event = eventservice.findById(id);
 
        return new ResponseEntity<Event>(event, new HttpHeaders(), HttpStatus.OK);
    }
 
   @PostMapping("/{id}")
    public ResponseEntity<Event> createOrUpdateEvent(@Validated @RequestBody Event event, @PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
	   	User user = userService.findById(id);
	   	event.setUser(user);
    	Event updated = eventservice.save(event);
        return new ResponseEntity<Event>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteEventById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        eventservice.deleteById(id);
        return HttpStatus.FORBIDDEN;
    }
 
}
