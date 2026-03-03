package com.example.JournalEntry.Repository;

import com.example.JournalEntry.Entity.CacheEntity;
import com.example.JournalEntry.Entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CacheRepo extends MongoRepository<CacheEntity, ObjectId> {


}
