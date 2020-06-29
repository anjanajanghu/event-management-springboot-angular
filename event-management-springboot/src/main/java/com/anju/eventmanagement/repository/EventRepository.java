package com.anju.eventmanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.anju.eventmanagement.model.Event;
@Repository
public interface EventRepository extends CrudRepository<Event, Long>{

}
