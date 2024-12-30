package com.socialmedia.backserver.controller;

import com.socialmedia.backserver.model.User;
import com.socialmedia.backserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        if (loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Username or password cannot be null"));
        }

        Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());
        if (user.isPresent()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
                return ResponseEntity.ok(Map.of("message", "Login successful!"));
            }
        }
        return ResponseEntity.status(401).body(Map.of("message", "Invalid username or password."));
    }
}

// DTO 用於接收前端數據
class LoginRequest {
    private String username;
    private String password;

    // Getters 和 Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}