package com.example.JournalEntry.Service;

import com.example.JournalEntry.Entity.User;
import com.example.JournalEntry.Repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Component
public class UserEntryService {
    @Autowired
    private UserRepo Userrepo;

    public List<User> GetallUser() {
        return Userrepo.findAll();
    }

    public User SaveUser( User Newuser) {
        return Userrepo.save(Newuser);
    }

    public boolean DeleteUser( ObjectId Id) {
        Userrepo.deleteById(Id);
        return true;
    }

    public User GetUserbyusername(String username) {
        return Userrepo.findByUsername(username);
    }




}