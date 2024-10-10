package com.hydrogenhr.service;

import com.hydrogenhr.persistence.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Optional<User> getUserByUsername(String username);

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserByUsernameOrEmail(String username, String email);

    User createUser(User user);

    List<User> getAllUsers();

    void deleteUser(UUID id);

    User updateUser(UUID id, User updatedUser);
}
