package com.donor.donorapp.controllers;

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
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){

        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updateUser){

        return userService.updateUser(id, updateUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){

        userService.deleteUser(id);
    }
}
