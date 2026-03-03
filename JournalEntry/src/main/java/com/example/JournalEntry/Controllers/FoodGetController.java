package com.example.JournalEntry.Controllers;

import com.example.JournalEntry.Entity.User;

import com.example.JournalEntry.Food.Food;
import com.example.JournalEntry.Food.Meal;
import com.example.JournalEntry.Service.FoodService;
import com.example.JournalEntry.Service.UserEntryService;
import com.example.JournalEntry.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/Getfood")

public class FoodGetController {
    @Autowired
    private UserEntryService userEntryService;
    @Autowired
    private FoodService foodService;

    @GetMapping
    public ResponseEntity<?> Getfood() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userEntryService.GetUserbyusername(name);
        String Str = "";
        if (user != null) {
            Meal meal = foodService.DisplayFooddetails("Arrabiata");
             Str = "Hi" + authentication.getName() + meal.getStrInstructions();
        }
        return  new ResponseEntity<>(Str, HttpStatus.FOUND);
    }
}
