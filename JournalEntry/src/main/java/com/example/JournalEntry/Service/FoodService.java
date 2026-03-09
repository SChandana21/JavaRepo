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
    @Autowired
    private RedisService redisService;

    public Meal DisplayFooddetails(String Fooditem) {
        Food food = redisService.get(Fooditem, Food.class);
        if (food != null && food.meals != null && !food.meals.isEmpty()) {
            return food.meals.get(0);
        } else {
            String FinalAPI = cache.Appcache.get("Food_API").replace("<food_item>", Fooditem);
            ResponseEntity<Food> exchange = restTemplate.exchange(FinalAPI, HttpMethod.GET, null, Food.class);
            Food body = exchange.getBody();
            if (body != null && body.meals != null && !body.meals.isEmpty()) {
                redisService.set("item", body.meals.get(0), 300l);
                return body.meals.get(0);
            } else {
                return null;
            }


        }
    }
}
