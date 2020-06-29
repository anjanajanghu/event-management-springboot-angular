package com.anju.eventmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="sessions")
public class Session {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Event event;

    private String name;
    
    private String presenter;
    
    private int duration;
    
    private String level;
    
    @Column(name = "abstract")
    @Lob
    private String sessionAbstract;

	public Session() {
		super();
	}

	public Session(Long id, Event event, String name, String presenter, int duration, String level,
			String sessionAbstract) {
		super();
		this.id = id;
		this.event = event;
		this.name = name;
		this.presenter = presenter;
		this.duration = duration;
		this.level = level;
		this.sessionAbstract = sessionAbstract;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPresenter() {
		return presenter;
	}

	public void setPresenter(String presenter) {
		this.presenter = presenter;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getSessionAbstract() {
		return sessionAbstract;
	}

	public void setSessionAbstract(String sessionAbstract) {
		this.sessionAbstract = sessionAbstract;
	}
	    
	
}

