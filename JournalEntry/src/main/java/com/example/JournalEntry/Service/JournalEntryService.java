package com.example.JournalEntry.Service;

import com.example.JournalEntry.Entity.JournalEntity;
import com.example.JournalEntry.Entity.User;
import com.example.JournalEntry.Repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component public class JournalEntryService  {
    @Autowired
    private JournalEntryRepo JournalEntryrepo;

    @Autowired
    private UserEntryService UserService;

    public void  SaveEntry(JournalEntity JournalEntry, String Username) {
        User userinDB = UserService.GetUserbyusername(Username);
        System.out.println(userinDB);
        JournalEntity Journal = JournalEntryrepo.save(JournalEntry);
        userinDB.getJournalentries().add(Journal);//try catch
        UserService.SaveUser(userinDB);

    }

    public void SaveEntry(JournalEntity Journal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        JournalEntryrepo.save(Journal); //saved in journal_entries
        User user = UserService.GetUserbyusername(name);
        if (user == null) {
            throw new RuntimeException("Authenticated user not found");
        }
        user.getJournalentries().add(Journal); //saved in users
        UserService.updateUser(user);
        //save in journal entries and also in User DB

    }



    public List<JournalEntity> GetJournalofauser () {
        System.out.println("Reached");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("reached 2");
        String name = authentication.getName();
        User user = UserService.GetUserbyusername(name);
        List<JournalEntity> journalentries = user.getJournalentries();
        if (journalentries != null && !journalentries.isEmpty()) {
            return journalentries;
        }
        return null;

    }

    public void DeleteEntry(ObjectId id, String Username) {
        User user = UserService.GetUserbyusername(Username);
        user.getJournalentries().removeIf(x -> x.getID().equals(id));
        UserService.SaveUser(user);
        JournalEntryrepo.deleteById(id);
    }

    public List<JournalEntity> GetJournalbyID (ObjectId Id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = UserService.GetUserbyusername(name);
        List<JournalEntity> collect = user.getJournalentries().stream().filter(x -> x.getID().equals(Id)).collect(Collectors.toList());
            return collect;


    }


    public void DeleteEntrybyID (ObjectId Id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = UserService.GetUserbyusername(name);
            JournalEntity journal = JournalEntryrepo.findById(Id).orElse(null);
            if (journal != null) {
                user.getJournalentries().stream().anyMatch(e -> e.getID().equals(Id)); {
                    JournalEntryrepo.deleteById(Id);
                }
            user.getJournalentries().removeIf(entry -> entry.getID().equals(Id));
            UserService.updateUser(user);
        }



}

            public JournalEntity UpdateJournalByID (ObjectId Id, JournalEntity newEntry) {
                    Authentication authentication =
                            SecurityContextHolder.getContext().getAuthentication();

                    String username = authentication.getName();
                    User user = UserService.GetUserbyusername(username);

                    if (user == null) {
                        return null;
                    }

                    boolean ownsJournal = user.getJournalentries()
                            .stream()
                            .anyMatch(j -> j.getID().equals(Id));

                    if (!ownsJournal) {
                        return null; // 401
                    }

                    Optional<JournalEntity> optionalJournal =
                            JournalEntryrepo.findById(Id);

                    if (optionalJournal.isEmpty()) {
                        return null;
                    }

                    JournalEntity journal = optionalJournal.get();

                    if (newEntry.getContent() != null &&
                            !newEntry.getContent().isBlank()) {

                        journal.setContent(newEntry.getContent());
                    }

                    if (newEntry.getTitle() != null &&
                            !newEntry.getTitle().isBlank()) {

                        journal.setTitle(newEntry.getTitle());
                    }

                    return JournalEntryrepo.save(journal);
                }
    }
