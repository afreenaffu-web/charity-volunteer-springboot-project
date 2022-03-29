package com.springboot.registeration.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;

    @Column(nullable = false, name = "event_name")
    @Getter @Setter
    private String eventName;

    @Column(nullable = false)
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter @Setter
    private Set<Volunteer> volunteerSet;


}