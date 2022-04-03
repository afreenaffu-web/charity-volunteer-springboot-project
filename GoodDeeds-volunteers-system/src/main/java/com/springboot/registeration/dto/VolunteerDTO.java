package com.springboot.registeration.dto;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VolunteerDTO {

    private Integer id;

    private String name;

    private String email;

    private String city;

    private EventDTO event;


}