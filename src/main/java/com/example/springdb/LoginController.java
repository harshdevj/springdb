package com.example.springdb;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        // Dummy validation logic (Replace with actual authentication logic)
        if ("admin".equals(username) && "password".equals(password)) {
            return "Login successful!";
        } else {
            return "Invalid credentials!";
        }
    }
}
