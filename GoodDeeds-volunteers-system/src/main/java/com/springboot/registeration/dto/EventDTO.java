package com.springboot.registeration.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
public class EventDTO {

    private Integer id;

    private String eventName;

    private Set<VolunteerDTO> volunteerSet;

    }