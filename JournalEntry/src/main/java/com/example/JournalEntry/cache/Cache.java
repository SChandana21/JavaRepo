package com.example.JournalEntry.cache;

import com.example.JournalEntry.Entity.CacheEntity;
import com.example.JournalEntry.Repository.CacheRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class Cache {
    @Autowired
    private CacheRepo cacheRepo;

     public Map<String, String> Appcache;

    @PostConstruct
    public void Initialize() {
        Appcache = new HashMap<>();
        List<CacheEntity> all = cacheRepo.findAll();
        for (CacheEntity cacheEntity : all) {
            Appcache.put(cacheEntity.getKey(), cacheEntity.getAPI());
            System.out.println(Appcache);
        }


    }

}
