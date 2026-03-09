package com.example.JournalEntry.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void RedisTest() {
        redisTemplate.opsForValue().set("email", "email@hotmail.com");
        Object email = redisTemplate.opsForValue().get("email");
        int a = 1;
    }
}
