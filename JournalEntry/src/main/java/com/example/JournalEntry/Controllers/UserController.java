package com.example.JournalEntry.Controllers;

import com.example.JournalEntry.Entity.User;
import com.example.JournalEntry.Service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserEntryService UserentryService;

    @GetMapping
    public List<User> GetAllUsers() {
        return  UserentryService.GetallUser();
    }

    @PostMapping
    public ResponseEntity<?> newUser(@RequestBody User newUser) {
        try {
            User savedUser = UserentryService.SaveUser(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving user: " + e.getMessage());
        }
    }


    @DeleteMapping("{Id}")
    public boolean DeleteUser(@PathVariable ObjectId Id) {
        UserentryService.DeleteUser(Id);
        return  true;
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> UpdateUser(@RequestBody User user, @PathVariable String username) {
        User userinDB = UserentryService.GetUserbyusername(username);
        if (userinDB != null) {
            userinDB.setUsername(user.getUsername());
            userinDB.setPassword(user.getPassword());
            UserentryService.SaveUser(userinDB);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{username}")
    public User GetUserBYID(@PathVariable String username) {
        System.out.println("HIT USERNAME METHOD");
        User user = UserentryService.GetUserbyusername(username);
        System.out.println(user);
         return user;

    }



}
