package com.org.LibraryApplication.service;

public interface EmailService {
    void sendEmailOnRegistration(String receiverEmail, String firstName, String lastName);
}
