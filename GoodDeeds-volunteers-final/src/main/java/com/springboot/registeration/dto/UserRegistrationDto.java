package com.springboot.registeration.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor  @AllArgsConstructor
public class UserRegistrationDto {
    @NotBlank(message = "firstName cannot be blank!!")
    @NotEmpty
    @Column(name = "first_name")
    @Getter @Setter
    private String firstName;

    @NotBlank(message = "LastName cannot be blank!!")
    @NotEmpty
    @Column(name = "last_name")
    @Getter @Setter
    private String lastName;

    @NotBlank(message = "email cannot be blank!!")
    @NotEmpty
    @Email
    @Getter @Setter
    private String email;

    @NotEmpty(message = "password cannot be empty!!")
    @NotBlank
    @Size(min=4, message = "length should be >=4")
    @Getter @Setter
    private String password;





}