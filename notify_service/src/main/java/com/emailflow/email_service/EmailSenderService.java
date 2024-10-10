package com.emailflow.email_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    JavaMailSender javaMailSender;

    public void sendMail(String toEmail , String subject , String body)
    {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("nithishmass718@gmail.com");
            message.setTo(toEmail);
            message.setText(body);
            message.setSubject(subject);
            javaMailSender.send(message);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
