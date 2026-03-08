package com.example.JournalEntry.Scheduler;

import com.example.JournalEntry.Entity.JournalEntity;
import com.example.JournalEntry.Entity.User;
import com.example.JournalEntry.Repository.UserDetailsIMPL;
import com.example.JournalEntry.Service.EmailService;
import com.example.JournalEntry.Service.SentimentalAnalysis;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class CronScheduler {
    @Autowired
    private UserDetailsIMPL userDetailsIMPL;
    @Autowired
    private EmailService emailService;
    @Autowired
    private SentimentalAnalysis sentimentalAnalysis;

    public void SendEmail () {
        List<User> users = userDetailsIMPL.GetuserByCriteria();
        for (User user: users) {
            List<JournalEntity> journalentries = user.getJournalentries();
            List<String> collect = journalentries.stream().map(x -> x.getTitle()).collect(Collectors.toList());
            String entry = String.join("", collect);
            System.out.println(entry);
            String sentiment = sentimentalAnalysis.getSentiment(entry);
            emailService.SendEmail(user.getUseremail(), "Sentimental Analysis", sentiment);

        }

    }
}
