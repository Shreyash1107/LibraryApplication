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
public class ValidationServiceImpl implements ValidationService{
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private RegisterRepository regRepo;
    @Override
    public List<String> validateRoles(RoleDto roleDto) {
        List<String> validateRole = new ArrayList<>();
        if(roleDto == null){
            validateRole.add("Role and Description is Required");
        }
        if(roleDto.getRoles().trim().isEmpty()){
            validateRole.add("Role is Required");
        }
        boolean isExist = roleRepo.existsByRoles(roleDto.getRoles());
        if(isExist){
            validateRole.add(roleDto.getRoles() + " already Exists");
        }
        if(roleDto.getDescription().trim().isEmpty()){
            validateRole.add("Role Description is Required");
        }
        if(!validateRole.isEmpty()){
            return validateRole;
        }
        return List.of();
    }

    @Override
    public List<String> validateUsers(RegisterDto regDto) {
        List<String> registerUsers = new ArrayList<>();
        if(regDto == null){
            registerUsers.add("All Fields are required");
        }
        if(regDto.getFirstName().trim().isEmpty()){
            registerUsers.add("First Name cannot be Empty");
        }
        return null;
    }
}