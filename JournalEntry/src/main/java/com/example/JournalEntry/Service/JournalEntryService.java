package com.example.JournalEntry.Service;

import com.example.JournalEntry.Entity.JournalEntity;
import com.example.JournalEntry.Entity.User;
import com.example.JournalEntry.Repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

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
        JournalEntryrepo.save(Journal);
    }

    public List <JournalEntity> GetAll() {
        return JournalEntryrepo.findAll();
    }

    public List<JournalEntity> GetJournalofauser (String Username) {
        User userfound = UserService.GetUserbyusername(Username);
          return  userfound.getJournalentries();
    }

    public void DeleteEntry(ObjectId id, String Username) {
        User user = UserService.GetUserbyusername(Username);
        user.getJournalentries().removeIf(x -> x.getID().equals(id));
        UserService.SaveUser(user);
        JournalEntryrepo.deleteById(id);
    }


}
