package com.springboot.registeration.controller;

import com.springboot.registeration.service.UserService;
import com.springboot.registeration.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    public RegistrationController(UserService userService) {

        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount( @Valid @ModelAttribute("user") UserRegistrationDto registrationDto,
                                       BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }
            try{
            userService.save(registrationDto);}
            catch(Exception exception){
             return "redirect:/registration?error";}

        return "redirect:/registration?success";}
}