package com.org.LibraryApplication.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class PasswordServiceImpl implements PasswordService{
    private String secretKey;
    public PasswordServiceImpl(@Value(("${security.encryption.key}")) String secretKey){
        this.secretKey = secretKey;
    }
    @Override
    public String encryptPassword(String password) {
        try{
            SecretKey secret = new SecretKeySpec(secretKey.getBytes(),"AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE,secret);
            byte[] encryptedPassword = cipher.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(encryptedPassword);
        } catch (Exception e) {
            throw new RuntimeException("Encryption Failed");
        }
    }
}
