package com.org.LibraryApplication.controller;

import com.org.LibraryApplication.dto.RegisterDto;
import com.org.LibraryApplication.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/register")
@CrossOrigin(origins = "*")
public class RegisterController {
    @Autowired
    private RegisterService regService;
    @PostMapping("/saveUsers")
    public ResponseEntity<List<String>> saveUsers(@RequestBody RegisterDto regDto){
        List<String> savedUsers = regService.registerUsers(regDto);
        if(savedUsers.get(0).contains("Successfully")){
            return new ResponseEntity<>(savedUsers, HttpStatus.OK);
        }else if (savedUsers.get(0).contains(" not found")){
            return new ResponseEntity<>(savedUsers,HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(savedUsers,HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<List<String>> loginUsers(@RequestBody Map<String,String> credentials){
        String email = credentials.get("email");
        String password = credentials.get("password");
        List<String> loginUser = regService.login(email,password);
        if(loginUser.get(0).contains("Login Success")){
            return new ResponseEntity<>(loginUser,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(loginUser,HttpStatus.BAD_REQUEST);
        }
    }
}