package com.donor.donorapp.dto;

public class UserResponseDto {

    private Long id;
    private String fName;
    private String lName;
    private String email;

    public UserResponseDto(){}

    public UserResponseDto(Long id, String fName, String lName, String email){
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.email = email;

    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

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
}
