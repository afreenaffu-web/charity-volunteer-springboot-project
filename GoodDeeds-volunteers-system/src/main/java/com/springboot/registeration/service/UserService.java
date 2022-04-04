package com.springboot.registeration.service;

import java.util.List;

import com.springboot.registeration.dto.UserRegistrationDTO;
import com.springboot.registeration.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    User save(UserRegistrationDTO registrationDto);
    List<User> getAll();
}
