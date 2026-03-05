package com.donor.donorapp.controllers;

import com.donor.donorapp.models.Role;
import com.donor.donorapp.models.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private List<User> users = new ArrayList<>();
    private Long idCounter = 1L;

    @PostMapping
    public User createUser(@RequestBody User user){
        for(User u : users){
            if(u.getEmail().equalsIgnoreCase(user.getEmail())){
                throw new RuntimeException("Email already exists.");
            }
        }
        user.setId(idCounter++);
        user.setRole(Role.USER);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        users.add(user);
        return user;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return users;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        for(User user : users){
            if(user.getId().equals(id)){
                return user;
            }
        }
        throw new RuntimeException("User not found.");
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updateUser){

        for(User user : users){

            if(user.getId().equals(id)){

                user.setFname(updateUser.getFname());
                user.setLname(updateUser.getLname());
                user.setEmail(updateUser.getEmail());
                user.setPassword(updateUser.getPassword());
                user.setUpdatedAt(LocalDateTime.now());

                return user;
            }
        }

        throw new RuntimeException("User not found.");
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){

        for(User user : users){

            if(user.getId().equals(id)){
                users.remove(user);
                return "User deleted";
            }
        }

        throw new RuntimeException("User not found");
    }
}
