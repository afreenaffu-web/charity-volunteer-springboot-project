package com.springboot.registeration.service;

import com.springboot.registeration.dto.VolunteerDTO;
import com.springboot.registeration.model.Event;
import com.springboot.registeration.model.Volunteer;
import com.springboot.registeration.repository.EventRepository;
import com.springboot.registeration.repository.VolunteerRepository;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VolunteerService {
    private VolunteerRepository volunteerRepository;
    private EventRepository eventRepository;
    private ModelMapper modelMapper;

    @Autowired
    public VolunteerService(VolunteerRepository volunteerRepository,
                            EventRepository eventRepository,
                            ModelMapper modelMapper) {
        this.volunteerRepository = volunteerRepository;
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }

    public Page<VolunteerDTO> getAllVolunteer(Pageable pageable) {

        return volunteerRepository.findAll(pageable)
                .map(x -> modelMapper.map(x, VolunteerDTO.class));
    }

    public void saveVolunteer(Volunteer volunteer) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Event event = eventRepository.findByEventName(volunteer.getEvent().getEventName());
        volunteer.setEvent(event);
        volunteerRepository.save(volunteer);
    }

    public void deleteVolunteer(Integer id) {
           volunteerRepository.deleteById(id);
    }

    public Optional<Volunteer> getVolunteerById(Integer id) {
        return volunteerRepository.findById(id);
    }

    public void saveAddVolunteer(VolunteerDTO volunteerDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Volunteer volunteer = modelMapper.map(volunteerDTO, Volunteer.class);
        Event event = eventRepository.findByEventName(volunteer.getEvent().getEventName());
        volunteer.setEvent(event);
        volunteerRepository.save(volunteer);

    }
}