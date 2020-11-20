package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Event;


@Repository
public interface EventRepository extends JpaRepository<Event, Long>{
	
	List<Event> findByCreatedByName(String username);
	
	List<Event> findByDescContaining(String descpription);


	

}
