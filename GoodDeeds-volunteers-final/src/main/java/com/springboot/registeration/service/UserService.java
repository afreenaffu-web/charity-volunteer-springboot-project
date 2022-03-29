package com.springboot.registeration.service;

import java.util.List;

import com.springboot.registeration.dto.UserRegistrationDto;
import com.springboot.registeration.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
    List<User> getAll();
}
