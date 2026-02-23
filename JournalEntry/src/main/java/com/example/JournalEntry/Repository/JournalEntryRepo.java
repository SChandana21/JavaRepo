package com.example.JournalEntry.Repository;

import com.example.JournalEntry.Entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepo extends MongoRepository<JournalEntity , ObjectId> {

}
