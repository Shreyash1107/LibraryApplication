package com.org.LibraryApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void sendEmailOnRegistration(String receiverEmail, String firstName,String lastName) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(receiverEmail);
        mailMessage.setFrom("shreyunkawane@gmail.com");
        mailMessage.setSubject("Registration Successful");
        mailMessage.setText("Hello, " + firstName + " " + lastName
                + "\n\nYour Registration is Successful!" + "\n\nThanks for Registration");
        mailSender.send(mailMessage);
    }
}
