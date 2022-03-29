package com.springboot.registeration.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "volunteer")
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    @Column(name = "name")
    private String name;

    @Getter @Setter
    @Column(name = "email")
    private String email;

    @Getter @Setter
    @Column(name = "city")
    private String  city;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    @Getter @Setter
    private Event event;



}