package com.example.demo.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.model.Customer;
import com.example.demo.model.Event;
import com.example.demo.service.CustomerService;
import com.example.demo.service.EventService;



@Controller
public class EventController {
	
    @Autowired
    
    private CustomerService cusService;
    
    @Autowired
    private EventService eventService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	 @GetMapping("/event/{eventId}")
	 public Event getCustomerById(@PathVariable String customername) {
	  return eventService.getEventByCustomerName(customername);
	 }
	 @GetMapping("/event/{desc}")
	 public List<Event> findDescEvents(@PathVariable String desc) {
     return eventService.findDescEvents(desc);
	 }
	 @PostMapping("/event/")
	 public ResponseEntity<Void> addEvent(@RequestBody Event newEvent, UriComponentsBuilder builder){
		
		eventService.save(newEvent, newEvent.getCreatedBy().toString() );
	  HttpHeaders headers = new HttpHeaders();
	  headers.setLocation(builder.path("/customer/{id}").buildAndExpand(newEvent.getId()).toUri());
	  return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 }
	 
	 @PutMapping("/event/")
	 public ResponseEntity<Event> updateEvent(@RequestBody Event event){
	  Event c = eventService.get(event.getId());
	  
	  if(c == null) {
	   return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
	  }
	  
	  c.setId(event.getId());	
	  c.setDesc(event.getDesc());
	  c.setCreatedBy(event.getCreatedBy());
	  c.setDone(true);
	  c.setTargetDate(event.getTargetDate());	 
	  eventService.updateEvent(c);
	  return new ResponseEntity<Event>(c, HttpStatus.OK);
	 }
	 
	 @DeleteMapping("/event/{eventId}")
	 public ResponseEntity<Event> deleteCustomer(@PathVariable Long eventId){
	  Event c = eventService.get(eventId);
	  
	  if(c == null) {
	   return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
	  }
	  
	  eventService.delete(eventId);
	  return new ResponseEntity<Event>(HttpStatus.NO_CONTENT);
	 }


	

}
