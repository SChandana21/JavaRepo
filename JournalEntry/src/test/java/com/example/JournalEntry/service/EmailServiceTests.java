package com.example.JournalEntry.service;

import com.example.JournalEntry.Service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    void testEmailservice() {
        emailService.SendEmail("chandrahasvalluri@gmail.com", "Sending Email...", "Hi!");
    }
}
