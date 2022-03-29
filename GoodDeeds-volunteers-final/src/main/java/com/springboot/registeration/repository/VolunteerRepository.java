package com.springboot.registeration.repository;


import com.springboot.registeration.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VolunteerRepository extends JpaRepository<Volunteer, Integer> {


}