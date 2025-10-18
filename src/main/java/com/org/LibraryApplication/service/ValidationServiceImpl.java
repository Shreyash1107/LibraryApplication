package com.org.LibraryApplication.service;

import com.org.LibraryApplication.dto.RegisterDto;
import com.org.LibraryApplication.dto.RoleDto;
import com.org.LibraryApplication.repository.RegisterRepository;
import com.org.LibraryApplication.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValidationServiceImpl implements ValidationService {
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private RegisterRepository regRepo;
    @Autowired
    private PasswordService passService;

    @Override
    public List<String> validateRoles(RoleDto roleDto) {
        List<String> validateRole = new ArrayList<>();
        if (roleDto == null) {
            validateRole.add("Role and Description is Required");
        }
        if (roleDto.getRoles().trim().isEmpty()) {
            validateRole.add("Role is Required");
        }
        boolean isExist = roleRepo.existsByRoles(roleDto.getRoles());
        if (isExist) {
            validateRole.add(roleDto.getRoles() + " already Exists");
        }
        if (roleDto.getDescription().trim().isEmpty()) {
            validateRole.add("Role Description is Required");
        }
        if (!validateRole.isEmpty()) {
            return validateRole;
        }
        return List.of();
    }

    @Override
    public List<String> validateUsers(RegisterDto regDto) {
        List<String> registerUsers = new ArrayList<>();
        boolean isEmailExists = regRepo.existsByEmail(regDto.getEmail());
        boolean isContactExists = regRepo.existsByContact(regDto.getContact());
        String decryptedPassword = passService.decryptPassword(regDto.getPassword());
        String decryptedConfirmPassword = passService.decryptPassword(regDto.getConfirmPassword());
        boolean isPasswordExists = regRepo.existsByPassword(decryptedPassword);
        if (regDto == null) {
            registerUsers.add("All Fields are required");
        }
        if (regDto.getFirstName().trim().isEmpty()) {
            registerUsers.add("First Name cannot be Empty");
        }
        if (regDto.getLastName().trim().isEmpty()) {
            registerUsers.add("Last Name Cannot be Empty");
        }
        if (regDto.getEmail().trim().isEmpty()) {
            registerUsers.add("Email is Required");
        }
        if (isEmailExists) {
            registerUsers.add("Email Already Exists");
        }
        if (regDto.getContact().isEmpty()) {
            registerUsers.add("Contact is Required");
        }
        if (isContactExists) {
            registerUsers.add("Contact Already Exists");
        }
        if (regDto.getPassword().trim().isEmpty()) {
            registerUsers.add("Password is Required");
        }
        if (isPasswordExists) {
            registerUsers.add("Password Already Exists");
        }
        if (regDto.getConfirmPassword().trim().isEmpty()) {
            registerUsers.add("Please Re-type the created Password");
        }
        if (!decryptedPassword.equals(decryptedConfirmPassword)) {
            registerUsers.add("Password and Confirm-Password must be Same");
        }
        if (regDto.getRolesAssigned() == null) {
            registerUsers.add("Please Provide Role Details");
        }
        if(!registerUsers.isEmpty()){
            return registerUsers;
        }
        return List.of();
    }
}