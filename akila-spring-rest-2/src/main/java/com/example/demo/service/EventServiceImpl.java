package com.example.demo.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;


@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	private CustomerService cusService;
	
    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event get(Long id) {
    	return eventRepository.getOne(id);
    }
    
    @Override
    public void delete(Long id) {
    	eventRepository.deleteById(id);
    }
    @Override
	public Event getEventByCustomerName(String Cusname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

    @Override
    public void save(Event event, String userName) {
    	Customer sessionUser = cusService.getCustomerByName(userName);
    	event.setCreatedBy(sessionUser);
    	eventRepository.save(event);
    }
    
    @Override
    public List<Event> findUserEvents(String username) {
    	return eventRepository.findByCreatedByName(username);
    }
    @Override
    public List<Event> findDescEvents(String descpription){
    	return  eventRepository.findByDescContaining(descpription);
    }



}
