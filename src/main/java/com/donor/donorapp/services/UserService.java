package com.donor.donorapp.services;

import com.donor.donorapp.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User createUser(User user);

    Page<User> getAllUsers(Pageable pageable);
    Page<User> searchUsers(String fname, String email, Pageable pageable);

    User getUserById(Long id);

    User updateUser(Long id, User user);

    void deleteUser(Long id);
}
