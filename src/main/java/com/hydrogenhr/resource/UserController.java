package com.hydrogenhr.resource;

import com.hydrogenhr.persistence.entity.User;
import com.hydrogenhr.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/dev")
@RequiredArgsConstructor
@Tag(name = "User endpoint")
public class UserController {

    private final UserService userService;



    @GetMapping("/user/username/{username}")
    public Optional<User> getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @GetMapping("/user/email/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }

    @GetMapping("/user/username-or-email")
    public Optional<User> getUserByUsernameOrEmail(@RequestParam(required = false) String username, @RequestParam(required = false) String email){
        if (username == null && email == null){
            throw new IllegalArgumentException("At least one of 'username' or 'email' must be provided.");
        }
        return userService.getUserByUsernameOrEmail(username, email);
    }

    @PostMapping("/user/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public  ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User updatedUser){
        return  new ResponseEntity<>(userService.updateUser(id, updatedUser), HttpStatus.OK);
    }

}
