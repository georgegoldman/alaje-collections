package com.hydrogenhr.service.impl;

import com.hydrogenhr.persistence.entity.User;
import com.hydrogenhr.persistence.repository.UserRepository;
import com.hydrogenhr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getUserByUsernameOrEmail(String username, String email) {
        return userRepository.findByUsernameOrEmail(username, email);
    }

    @Override
    public User createUser(User user){
        // Check if a user with the same username or email already exists
        Optional<User> existingUserByUsernameorEmail  = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());

        if (existingUserByUsernameorEmail.isPresent()){
            throw new IllegalArgumentException("Username already exists: ");
        }
        // save the new user
        return  userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(UUID id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setEmail(updatedUser.getEmail());
            user.setTelephone(updatedUser.getTelephone());
            user.setPasswordLastResetDate(updatedUser.getPasswordLastResetDate());
            user.setPasswordReset(updatedUser.isPasswordReset());
            user.setDateOfBirth(updatedUser.getDateOfBirth());
            user.setAdmin(updatedUser.isAdmin());
            user.setUsing2FA(updatedUser.isUsing2FA());
            user.setAccountType(updatedUser.getAccountType());
            user.setUserType(updatedUser.getUserType());
            user.setRegistrationStage(updatedUser.getRegistrationStage());
            user.setCountry(updatedUser.getCountry());
            user.setOrganization(updatedUser.getOrganization());
            user.setRoles(updatedUser.getRoles());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
