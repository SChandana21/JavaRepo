package com.example.JournalEntry.Controllers;

import com.example.JournalEntry.Entity.JournalEntity;
import com.example.JournalEntry.Entity.User;
import com.example.JournalEntry.Service.JournalEntryService;
import com.example.JournalEntry.Service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JavaController {
    @Autowired
    private JournalEntryService journalEntryService;
    //This will be a REST Controller - HTTP with endpoints
    @Autowired
    private UserEntryService Userservice;


        @GetMapping
        public ResponseEntity<?> GetJournalEntriesofUser(@PathVariable String Username) {
            User user = Userservice.GetUserbyusername(Username);
            List <JournalEntity> L =  user.getJournalentries();
            if (L != null && L.isEmpty()) {
                return new ResponseEntity<>(L, HttpStatus.FOUND);
            }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        @Transactional
        @PostMapping("{Username}")
    public ResponseEntity <JournalEntity> PostEntries(@RequestBody JournalEntity E, @PathVariable String Username) {
            try {
                E.setDate(LocalDateTime.now());
                journalEntryService.SaveEntry(E, Username);
                return new ResponseEntity<>(E, HttpStatus.CREATED);
            } catch (Exception e) {
                System.out.println(e);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }


        @GetMapping("id/{myId}")
    public ResponseEntity<?> GetJournalEntrybyID (@PathVariable ObjectId myId) {
            Optional <JournalEntity> journalEntity = journalEntryService.GetByID(myId);
            if (journalEntity.isPresent()) {
                return new ResponseEntity<>(journalEntity.get(), HttpStatus.FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @DeleteMapping("id/{Username}/{myId}")
    public ResponseEntity<?> DeleteJournalEntry(@PathVariable String Username, @PathVariable ObjectId myId) {
            journalEntryService.DeleteEntry(myId, Username);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


       @PutMapping("/id/{userName}/{Id}")
  public JournalEntity UpdateEntry(@PathVariable String userName, @PathVariable ObjectId Id, @RequestBody JournalEntity newEntry) {
    JournalEntity old = journalEntryService.GetByID(Id).orElse(null);
    if (old != null) {
         old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
        old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
     }
    journalEntryService.SaveEntry(old);
      return old;
   }

}
