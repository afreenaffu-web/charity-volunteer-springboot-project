package com.springboot.registeration.dto;


import lombok.Getter;
import lombok.Setter;

public class VolunteerDTO {
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String city;

    @Getter @Setter
    private EventDTO event;


}