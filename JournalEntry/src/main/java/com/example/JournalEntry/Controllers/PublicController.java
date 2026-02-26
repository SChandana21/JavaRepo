package com.example.JournalEntry.Controllers;

import com.example.JournalEntry.Entity.User;
import com.example.JournalEntry.Service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
@RestController
@RequestMapping("/user")
public class PublicController {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserEntryService UserentryService;
    @Transactional
    @PostMapping
    public ResponseEntity<?> newUser(@RequestBody User newUser) {
        System.out.println("POST /user hit");
        try {
            User savedUser = UserentryService.SaveUser(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving user: " + e.getMessage());
        }
    }
}
