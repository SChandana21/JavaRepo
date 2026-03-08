package com.example.JournalEntry;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class JournalEntryApplication {



    @Bean
    public CommandLineRunner debugMongo(MongoTemplate mongoTemplate) {
        return args -> {
            System.out.println("- MONGO DEBUG -");
            System.out.println("Database Name: " + mongoTemplate.getDb().getName());
            System.out.println("Factory Class: " + mongoTemplate.getMongoDatabaseFactory().getClass());
            System.out.println("-----------------------");
        };
    }
    @Bean
    public RestTemplate restTemplate () {
        return new RestTemplate();
    }

    @Value("${spring.mongodb.uri:NOT_FOUND}")
    private String uri;
    @PostConstruct
    public void checkUri() {
        System.out.println(">>> URI VALUE: " + uri);
    }


    @Autowired
    private Environment env;
    @PostConstruct
    public void verifyProperty() {
        System.out.println("Property value: " + env.getProperty("spring.mongodb.uri"));
    }


	public static void main(String[] args) {
		SpringApplication.run(JournalEntryApplication.class, args);
	}
        @Bean
    public PlatformTransactionManager F(MongoDatabaseFactory dbFactory) {
            return new MongoTransactionManager(dbFactory);
        }


}
