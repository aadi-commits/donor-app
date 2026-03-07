package com.donor.donorapp.controllers;

import com.donor.donorapp.dto.UserRequestDto;
import com.donor.donorapp.dto.UserResponseDto;
import com.donor.donorapp.mapper.UserMapper;
import com.donor.donorapp.models.User;
import com.donor.donorapp.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDto createUser(@RequestBody UserRequestDto request){
        User user = UserMapper.toEntity(request);
        User saveUser = userService.createUser(user);
        return UserMapper.toDto(saveUser);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return users.stream()
                .map(UserMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        return UserMapper.toDto(user);
    }

    @PutMapping("/{id}")
    public UserResponseDto updateUser(@PathVariable Long id,
                                      @RequestBody UserRequestDto request){

        User user = UserMapper.toEntity(request);
        User updateUser = userService.updateUser(id, user);
        return UserMapper.toDto(updateUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){

        userService.deleteUser(id);
    }
}
