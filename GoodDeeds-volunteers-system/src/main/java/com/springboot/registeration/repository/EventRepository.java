package com.springboot.registeration.repository;


import com.springboot.registeration.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {

    Event findByEventName(String name);
}