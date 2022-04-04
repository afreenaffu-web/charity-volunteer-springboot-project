package com.springboot.registeration.service;

import com.springboot.registeration.dto.EventDTO;
import com.springboot.registeration.model.Event;
import com.springboot.registeration.repository.EventRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private EventRepository eventRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<EventDTO> getAllEvent() {
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .map(this::convertToEventToEventDTO).collect(Collectors.toList());
    }

    public EventDTO convertToEventToEventDTO(Event event) {
        return modelMapper.map(event, EventDTO.class);
    }
}