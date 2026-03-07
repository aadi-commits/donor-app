package com.donor.donorapp.mapper;

import com.donor.donorapp.dto.UserRequestDto;
import com.donor.donorapp.dto.UserResponseDto;
import com.donor.donorapp.models.User;

public class UserMapper {

    public static UserResponseDto toDto(User user){

        UserResponseDto dto = new UserResponseDto();

        dto.setId(user.getId());
        dto.setFName(user.getFname());
        dto.setLName(user.getLname());
        dto.setEmail(user.getEmail());

        return dto;
    }

    public static User toEntity(UserRequestDto dto){

        User user = new User();

        user.setFname(dto.getFName());
        user.setLname(dto.getLName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return user;
    }
}
