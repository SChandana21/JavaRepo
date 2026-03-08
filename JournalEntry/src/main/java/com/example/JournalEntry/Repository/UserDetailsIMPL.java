package com.example.JournalEntry.Repository;

import com.example.JournalEntry.Entity.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDetailsIMPL {
    public UserDetailsIMPL () {
        System.out.println("Beannnn");
    }

    @PostConstruct
    public void init() {
        System.out.println(">>> MongoTemplate injected: " + mongoTemplate);
    }
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> GetuserByCriteria() {
        Query query  = new Query();
        Criteria emailcriteria = Criteria.where("Useremail").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        System.out.println(emailcriteria);
        Criteria sentimentalcriteria = Criteria.where("sentimentalAnalysis").is(true);
         query.addCriteria(new Criteria().orOperator(emailcriteria, sentimentalcriteria));
        List<User> users = mongoTemplate.find(query, User.class);
        for (User userr: users) {
            System.out.println(userr.getUseremail());
        }
        System.out.println(users);
        return mongoTemplate.find(query, User.class);
    }
}
