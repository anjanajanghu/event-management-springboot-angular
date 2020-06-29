package com.anju.eventmanagement.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.anju.eventmanagement.model.Event;
import com.anju.eventmanagement.repository.EventRepository;

@Service
public class EventServiceImp implements EventService {
	
	private final EventRepository eventRepository;
	
	public EventServiceImp(EventRepository eventRepository) {
		super();
		this.eventRepository = eventRepository;
	}

	@Override
	public Set<Event> findAll() {
		
		Set<Event> events = new HashSet<Event>();
		eventRepository.findAll().forEach(events::add);
		return events;
	}

	@Override
	public Event findById(Long id) {
//		Optional<Event> event = eventRepository.findById(id);
//        
//        if(event.isPresent()) {
//            return event.get();
//        } else {
//            throw new RecordNotFoundException("No event record exist for given id",id);
//        }
		
        return eventRepository.findById(id).orElse(null);
	}

	@Override
	public Event save(Event object) {
		return eventRepository.save(object);
	}

	@Override
	public void delete(Event object) {
		 eventRepository.delete(object);
		
	}

	@Override
	public void deleteById(Long id) {
		eventRepository.deleteById(id);
		
	}

}
