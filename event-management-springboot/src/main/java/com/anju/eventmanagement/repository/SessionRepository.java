package com.anju.eventmanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.anju.eventmanagement.model.Session;
@Repository
public interface SessionRepository extends CrudRepository<Session, Long>{

}
