package com.hydrogenhr.service;

import com.hydrogenhr.persistence.entity.User;

import java.lang.Long;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserByUsername(String username);

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserByUsernameOrEmail(String username, String email);

    User createUser(User user);

    List<User> getAllUsers();

    void deleteUser(Long id);

    User updateUser(Long id, User updatedUser);

    Optional<User> getUserById(Long id);
}
