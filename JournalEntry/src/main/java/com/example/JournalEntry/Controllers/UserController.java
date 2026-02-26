package com.example.JournalEntry.Controllers;

import com.example.JournalEntry.Entity.User;
import com.example.JournalEntry.Service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")

public class UserController {



    @Autowired
    private UserEntryService UserentryService;





    @DeleteMapping("{Id}")
    public boolean DeleteUser(@PathVariable ObjectId Id) {
        UserentryService.DeleteUser(Id);
        return  true;
    }



    @GetMapping("{username}")
    public User GetUserBYID(@PathVariable String username) {
        System.out.println("HIT USERNAME METHOD");
        User user = UserentryService.GetUserbyusername(username);
        System.out.println(user);
         return user;

    }

    @PutMapping
    public ResponseEntity<?> Edituserdetails(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userinDB = UserentryService.GetUserbyusername(username);
        userinDB.setUsername(user.getUsername());
        userinDB.setPassword(user.getPassword());
        UserentryService.SaveUser(userinDB);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }



}
