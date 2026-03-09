package com.example.JournalEntry.Entity;

import com.example.JournalEntry.Enum.Sentiment;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
public class JournalEntity {
    @Id
    private ObjectId ID;
    @NonNull
    private String title;
    private String Content;
    private LocalDateTime date;
    private Sentiment sentiment;


}
