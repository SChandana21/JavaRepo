    package com.example.JournalEntry.Controllers;

    import com.example.JournalEntry.Entity.JournalEntity;
    import com.example.JournalEntry.Entity.User;
    import com.example.JournalEntry.Service.JournalEntryService;
    import com.example.JournalEntry.Service.UserEntryService;
    import org.bson.types.ObjectId;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.context.SecurityContextHolder;
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
            public ResponseEntity<List<JournalEntity>> GetJournalEntriesofUser() {
                List<JournalEntity> journalEntities = journalEntryService.GetJournalofauser();
                if (journalEntities != null && !journalEntities.isEmpty()) {
                    return ResponseEntity.ok(journalEntities);
                }
                return ResponseEntity.noContent().build();
            }
            @Transactional
            @PostMapping
        public ResponseEntity <JournalEntity> PostEntries(@RequestBody JournalEntity E) {
                try {
                    journalEntryService.SaveEntry(E);
                    return new ResponseEntity<>(E, HttpStatus.CREATED);
                } catch (Exception e) {
                    System.out.println(e);
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }




            @DeleteMapping("id/{myId}")
        public ResponseEntity<?> DeleteJournalEntry(@PathVariable ObjectId myId) {
            journalEntryService.DeleteEntrybyID(myId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }


         @GetMapping("/id/{Id}")
        public ResponseEntity<List<JournalEntity>> GetJournalByID (@PathVariable ObjectId    Id) {
             List<JournalEntity> journalEntities = journalEntryService.GetJournalbyID(Id);
             if (!journalEntities.isEmpty()) {
                 return new ResponseEntity<>(journalEntities, HttpStatus.FOUND);
             }
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
             }

        @PutMapping("/id/{Id}")
        public ResponseEntity<JournalEntity> UpdateJournalByID (@PathVariable ObjectId Id, @RequestBody JournalEntity NewJournal) {
            JournalEntity journal = journalEntryService.UpdateJournalByID(Id, NewJournal);
            return new ResponseEntity<>(journal, HttpStatus.CREATED);
        }
    }
