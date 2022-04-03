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
@Getter @Setter
public class UserRegistrationDTO {
    @NotBlank(message = "First Name cannot be blank!!")
    @NotEmpty
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last Name cannot be blank!!")
    @NotEmpty
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Email cannot be blank!!")
    @NotEmpty
    @Email
    private String email;

    @NotEmpty(message = "Password cannot be empty!!")
    @NotBlank
    @Size(min=4, message = "length should be >=4")
    private String password;





}