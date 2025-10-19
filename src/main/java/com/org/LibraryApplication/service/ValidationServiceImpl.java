package com.org.LibraryApplication.service;

import com.org.LibraryApplication.dto.RegisterDto;
import com.org.LibraryApplication.dto.RoleDto;
import com.org.LibraryApplication.mapper.RegisterMapper;
import com.org.LibraryApplication.repository.RegisterRepository;
import com.org.LibraryApplication.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ValidationServiceImpl implements ValidationService {
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private RegisterRepository regRepo;
    @Autowired
    private PasswordService passService;
    @Autowired
    private RegisterMapper regMapper;
    @Override
    public List<String> validateRoles(RoleDto roleDto) {
        List<String> validateRole = new ArrayList<>();
        if (roleDto == null) {
            validateRole.add("Role and Description is Required");
        }if (roleDto.getRoles().trim().isEmpty()) {
            validateRole.add("Role is Required");
        }boolean isExist = roleRepo.existsByRoles(roleDto.getRoles());
        if (isExist) {
            validateRole.add(roleDto.getRoles() + " already Exists");
        }if (roleDto.getDescription().trim().isEmpty()) {
            validateRole.add("Role Description is Required");
        }if (!validateRole.isEmpty()) {
            return validateRole;
        }
        return List.of();
    }

    @Override
    public List<String> validateUsers(RegisterDto regDto) {
        List<String> registerUsers = new ArrayList<>();
        boolean isEmailExists = regRepo.existsByEmail(regDto.getEmail());
        boolean isContactExists = regRepo.existsByContact(regDto.getContact());
        if (regDto == null) {
            registerUsers.add("All Fields are required");
        }if (regDto.getFirstName().trim().isEmpty()) {
            registerUsers.add("First Name cannot be Empty");
        }if (regDto.getLastName().trim().isEmpty()) {
            registerUsers.add("Last Name Cannot be Empty");
        }if (regDto.getEmail().trim().isEmpty()) {
            registerUsers.add("Email is Required");
        }if (isEmailExists) {
            registerUsers.add("Email Already Exists");
        }if (regDto.getContact().isEmpty()) {
            registerUsers.add("Contact is Required");
        }if (isContactExists) {
            registerUsers.add("Contact Already Exists");
        }if (regDto.getPassword().trim().isEmpty()) {
            registerUsers.add("Password is Required");
        }if (regDto.getConfirmPassword().trim().isEmpty()) {
            registerUsers.add("Please Re-type the created Password");
        }if (regDto.getRolesAssigned() == null) {
            registerUsers.add("Please Provide Role Details");
        }if (!regDto.getPassword().equals(regDto.getConfirmPassword())) {
            registerUsers.add("PassWord and Confirm Password should match");
        }
        String enteredPassword = regDto.getPassword();
        List<RegisterDto> registeredUsers = regRepo.findAll()
                .stream().map(regMapper::entityToDto).collect(Collectors.toList());
        for (RegisterDto reg : registeredUsers) {
            String decryptedPassword = passService.decryptPassword(reg.getPassword());
            if (decryptedPassword.equals(enteredPassword)) {
                registerUsers.add("Password Already Exists");
                break;
            }
        }
        if (!registerUsers.isEmpty()) {
            return registerUsers;
        }
        return List.of();
    }
}