package com.example.JournalEntry.Controllers;

import com.example.JournalEntry.Entity.User;
import com.example.JournalEntry.Repository.UserRepo;
import com.example.JournalEntry.Service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserEntryService userEntryService;
    @GetMapping
    public ResponseEntity<List<?>> GetallUsersDetails() {
        System.out.println("Reached");
        List<User> all = userRepo.findAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        }


        @PostMapping
    public void CreateNewAdmin(@RequestBody User NewAdmin) {
        userEntryService.SaveAdmin(NewAdmin);
        }
}
