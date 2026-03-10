package com.donor.donorapp.controllers;

import com.donor.donorapp.dto.UserRequestDto;
import com.donor.donorapp.dto.UserResponseDto;
import com.donor.donorapp.mapper.UserMapper;
import com.donor.donorapp.models.User;
import com.donor.donorapp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponseDto createUser(@Valid @RequestBody UserRequestDto request){
        User user = UserMapper.toEntity(request);
        User saveUser = userService.createUser(user);
        return UserMapper.toDto(saveUser);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Page<UserResponseDto> getAllUsers(
            @RequestParam(required = false) String fname,
            @RequestParam(required = false) String email,
            Pageable pageable){
        Page<User> users;

        if(fname != null || email != null){
            users = userService.searchUsers(fname, email, pageable);
        }else {
            users = userService.getAllUsers(pageable);
        }

        return users.map(UserMapper::toDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponseDto getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        return UserMapper.toDto(user);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponseDto updateUser(@PathVariable Long id,
                                      @Valid @RequestBody UserRequestDto request){

        User user = UserMapper.toEntity(request);
        User updateUser = userService.updateUser(id, user);
        return UserMapper.toDto(updateUser);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable Long id){

        userService.deleteUser(id);
    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public String getCurrentUser() {

        return "User profile visible";
    }
}
