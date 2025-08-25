package com.bizzcart.user_service.controller;

import com.bizzcart.user_service.dto.User;
import com.bizzcart.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> register(@RequestBody User user) {
        try {
            User savedUser = userService.registerUser(user);

            Map<String, Object> body = new HashMap<>();
            body.put("id", savedUser.getUser_id());
            body.put("message", "User registered successfully");

            URI location = URI.create("/users/" + savedUser.getUser_id());
            return ResponseEntity.created(location).body(body);

        } catch (ResponseStatusException ex) {
            Map<String, Object> body = new HashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("status", ex.getStatusCode().value());
            body.put("error", ex.getStatusCode());
            body.put("message", ex.getReason());
            return new ResponseEntity<>(body, ex.getStatusCode());
        } catch (Exception ex) {
            Map<String, Object> body = new HashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.put("error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            body.put("message", ex.getMessage());
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/findByEmail")
    public ResponseEntity<User> getUserByEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }
}

