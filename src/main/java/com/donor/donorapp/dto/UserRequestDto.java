package com.donor.donorapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequestDto {

    @NotBlank(message = "Last name is required.")
    private String lName;

    @NotBlank(message = "First name is required.")
    private String fName;

    @Email(message = "Email must be valid.")
    @NotBlank(message = "Email is required.")
    private  String email;

    @Size(min = 6, message = "Password must be at least 6 characters.")
    @NotBlank(message = "Password is required.")
    private String password;

    public UserRequestDto(){}

    public String getFName(){
        return fName;
    }

    public void setFName(String fName){
        this.fName = fName;
    }

    public String getLName(){
        return lName;
    }

    public void setLName(String lName){
        this.lName = lName;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
