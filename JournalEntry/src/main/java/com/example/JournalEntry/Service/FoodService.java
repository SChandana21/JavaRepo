package com.example.JournalEntry.Service;

import com.example.JournalEntry.Food.Food;

import com.example.JournalEntry.Food.Meal;
import com.example.JournalEntry.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class FoodService {


    @Value("${food.api.base-url}")
    private String API;
    @Autowired
    private Cache cache;
    @Autowired
    private RestTemplate restTemplate;

    public Meal DisplayFooddetails(String Fooditem) {
    String FinalAPI = cache.Appcache.get("Food_API").replace("<food_item>", Fooditem);
         ResponseEntity<Food> exchange = restTemplate.exchange(FinalAPI, HttpMethod.GET, null, Food.class);
        Food body = exchange.getBody();
         return body != null && body.meals != null && !body.meals.isEmpty()
                ? body.meals.get(0)
                : null;

    }
}
