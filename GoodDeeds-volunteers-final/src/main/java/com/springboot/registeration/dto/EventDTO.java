package com.springboot.registeration.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;

public class EventDTO {
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String eventName;

    @Getter @Setter
    private Set<VolunteerDTO> volunteerSet;

    }