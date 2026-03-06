package com.donor.donorapp.controllers;

import com.donor.donorapp.models.Role;
import com.donor.donorapp.models.User;
import com.donor.donorapp.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        if(userRepository.existsByEmailIgnoreCase(user.getEmail())){
            throw new RuntimeException("Email already exists.");
        }

        user.setRole(Role.USER);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){

        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found."));
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updateUser){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found."));

        user.setFname(updateUser.getFname());
        user.setLname(updateUser.getLname());
        user.setEmail(updateUser.getEmail());
        user.setPassword(updateUser.getPassword());
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){

        if(!userRepository.existsById(id)){
            throw new RuntimeException("User not found");
        }

        userRepository.deleteById(id);

        return "User deleted successfully";
    }
}
