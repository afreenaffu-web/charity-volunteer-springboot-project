package com.springboot.registeration.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.springboot.registeration.dto.UserRegistrationDto;
import com.springboot.registeration.model.Role;
import com.springboot.registeration.model.User;
import com.springboot.registeration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
            User user;
            String email=registrationDto.getEmail();
        Role role1=new Role("ROLE_ADMIN");
        Role role2=new Role("ROLE_USER");
        if(email.contains("@core-admin.com"))
            user = getUserWithRole( registrationDto, role1);
        else
            user = getUserWithRole(registrationDto, role2);

        return userRepository.save(user);
    }

    public User getUserWithRole(UserRegistrationDto registrationDto, Role role1) {
        return new User(registrationDto.getFirstName(),
                registrationDto.getLastName(), registrationDto.getEmail(),
                passwordEncoder.encode(registrationDto.getPassword()),
                Arrays.asList(role1));

    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException
                    ("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.
                User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities
            (Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority
                (role.getName())).collect(Collectors.toList());
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
