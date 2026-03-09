package com.example.JournalEntry.Scheduler;

import com.example.JournalEntry.Entity.JournalEntity;
import com.example.JournalEntry.Entity.User;
import com.example.JournalEntry.Enum.Sentiment;
import com.example.JournalEntry.Repository.UserDetailsIMPL;
import com.example.JournalEntry.Service.EmailService;
import com.example.JournalEntry.Service.SentimentalAnalysis;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class CronScheduler {
    @Autowired
    private UserDetailsIMPL userDetailsIMPL;
    @Autowired
    private EmailService emailService;



    public void SendEmail () {
        List<User> users = userDetailsIMPL.GetuserByCriteria();
        for (User user: users) {
            List<JournalEntity> journalentries = user.getJournalentries();
            List<Sentiment> collectionSentiments = journalentries.stream().map(x -> x.getSentiment()).collect(Collectors.toList());
            Map <Sentiment, Integer> Sentimentmap = new HashMap<>();
                for (Sentiment sentiment : collectionSentiments) {
                    if (sentiment != null)
                        Sentimentmap.put(sentiment, Sentimentmap.getOrDefault(sentiment, 0) + 1);
                }
                    Sentiment FrequentSentiment = null;
                    int maxcount = 0;
                    for (Map.Entry<Sentiment, Integer> entry: Sentimentmap.entrySet()) {
                        if (entry.getValue() > maxcount) {
                            maxcount = entry.getValue();
                            FrequentSentiment = entry.getKey();
                        }
                    }
                    if (FrequentSentiment != null) {
                        emailService.SendEmail(user.getUseremail(), "Sentiment for the  7 days", FrequentSentiment.toString());
                    }




        }

    }
}
