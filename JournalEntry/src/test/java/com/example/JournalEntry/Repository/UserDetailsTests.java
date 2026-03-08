package com.example.JournalEntry.Repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.JournalEntry.Repository.UserDetailsIMPL;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class UserDetailsTests {


    @Autowired
    private UserDetailsIMPL userDetailsIMPL;

    @Test
    public void TestuserDetails() {
        userDetailsIMPL.GetuserByCriteria();
    }
}
