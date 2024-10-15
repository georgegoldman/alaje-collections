package com.hydrogenhr.resource;

import com.hydrogenhr.model.exceptions.UserRegistrationValidator;
import com.hydrogenhr.persistence.entity.User;
import com.hydrogenhr.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dev/user")
@RequiredArgsConstructor
@Tag(name = "User endpoint")
public class UserController {

    private final UserService userService;



    @GetMapping("/username/{username}")
    public Optional<User> getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @GetMapping("/email/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }

    @GetMapping("/username-or-email")
    public Optional<User> getUserByUsernameOrEmail(@RequestParam(required = false) String username, @RequestParam(required = false) String email){
        if (username == null && email == null){
            throw new IllegalArgumentException("At least one of 'username' or 'email' must be provided.");
        }
        return userService.getUserByUsernameOrEmail(username, email);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User userRequest){
        UserRegistrationValidator.validate(userRequest);
        User newUser = userService.createUser(userRequest);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser){
        return  new ResponseEntity<>(userService.updateUser(id, updatedUser), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id){
        return userService.getUserById(id);
        
    }

}
