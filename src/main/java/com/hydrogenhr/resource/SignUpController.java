package com.hydrogenhr.resource;

import com.hydrogenhr.persistence.entity.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dev")
@RequiredArgsConstructor
@Tag(name = "Sign up endpoint")
public class SignUpController {

    private final UserController userController;

    @PostMapping("/auth/verify")
    public ResponseEntity<String> signUpUser(@Valid @RequestBody User request){
        // Todo Proceed with further logic, sending a verification email or SMS.

        userController.createUser(request);
        return ResponseEntity.ok("Verification initiated successfully");
    }
}
