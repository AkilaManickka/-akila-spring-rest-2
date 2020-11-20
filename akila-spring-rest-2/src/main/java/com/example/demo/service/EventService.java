package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Event;



public interface EventService {
	
	Event get(Long id);

	void delete(Long id);

	void save(Event event, String userName);
	 public Event getEventByCustomerName(String Cusname);
	 public void updateEvent(Event event);

	List<Event> findUserEvents(String username);
	List<Event> findDescEvents(String descpription);

	

}
