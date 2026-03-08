package com.example.JournalEntry.Cron;

import com.example.JournalEntry.Scheduler.CronScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CronSchedulerTests {
    @Autowired
    private CronScheduler cronScheduler;
@Test
    public void Fetchuserandsendsaemail() {
    cronScheduler.SendEmail();
}
}
