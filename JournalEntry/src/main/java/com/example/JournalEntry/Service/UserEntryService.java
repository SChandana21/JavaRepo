package com.example.JournalEntry.Service;

import com.example.JournalEntry.Entity.User;
import com.example.JournalEntry.Repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
public class UserEntryService {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepo Userrepo;

    //

    public User SaveUser( User Newuser) {
        Newuser.setPassword(passwordEncoder.encode(Newuser.getPassword()));
        Newuser.setRoles(Arrays.asList("USER"));
        return Userrepo.save(Newuser);
    }

    public User SaveAdmin( User Newuser) {
        Newuser.setPassword(passwordEncoder.encode(Newuser.getPassword()));
        Newuser.setRoles(Arrays.asList("USER", "ADMIN"));
        return Userrepo.save(Newuser);
    }

    public void updateUser(User user) {
        Userrepo.save(user); // no encoding here
    }

    public boolean DeleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = Userrepo.findByUsername(name);
        Userrepo.delete(user);
        return  true;
    }

    public User GetUserbyusername(String username) {
        return Userrepo.findByUsername(username);
    }




}