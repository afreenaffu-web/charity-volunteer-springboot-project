package com.springboot.registeration.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "volunteer")
@Getter @Setter
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "city")
    private String  city;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;



}