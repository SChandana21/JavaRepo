package com.example.JournalEntry.Service;


import com.example.JournalEntry.Repository.UserDetailsIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import  org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;


    public void SendEmail(String To, String Subject, String Body) {
            try {
                SimpleMailMessage Mail = new SimpleMailMessage();
                Mail.setTo(To);
                Mail.setSubject(Subject);
                Mail.setText(Body);
                javaMailSender.send(Mail);
            } catch (Exception e) {
                System.out.println(e);
            }
    }


}
