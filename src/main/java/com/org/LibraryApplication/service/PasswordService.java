package com.org.LibraryApplication.service;

public interface PasswordService {
    String encryptPassword(String password);
    String decryptPassword(String encryptedPassword);
}
