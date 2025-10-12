package com.org.LibraryApplication.controller;

import com.org.LibraryApplication.dto.RegisterDto;
import com.org.LibraryApplication.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private RegisterService regService;
    @PostMapping("/saveUsers")
    public ResponseEntity<List<String>> saveUsers(@RequestBody RegisterDto regDto){
        List<String> savedUsers = regService.registerUsers(regDto);
        if(savedUsers.get(0).contains("Saved Successfully")){
            return new ResponseEntity<>(savedUsers, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(savedUsers,HttpStatus.NOT_FOUND);
        }
    }
}