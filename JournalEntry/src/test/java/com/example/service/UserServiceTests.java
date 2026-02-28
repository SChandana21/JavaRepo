package com.example.service;

import com.example.JournalEntry.Entity.User;
import com.example.JournalEntry.JournalEntryApplication;
import com.example.JournalEntry.Repository.UserRepo;
import com.example.JournalEntry.Service.UserEntryService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = JournalEntryApplication.class)
public class UserServiceTests {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserEntryService userEntryService;
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void TestCreatingUserBoolean(User user) {
        assertTrue(userEntryService.BooleanSaveuser(user));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1, 1, 2",
            "2, 10, 12",
            "3, 3, 7"
    })
    public void Param(int a, int b, int expected) {
        assertEquals(expected, a + b);
    }
}
