package com.org.LibraryApplication.service;

import com.org.LibraryApplication.dto.RegisterDto;
import com.org.LibraryApplication.entity.RegisterEntity;
import com.org.LibraryApplication.mapper.RegisterMapper;
import com.org.LibraryApplication.repository.RegisterRepository;
import com.org.LibraryApplication.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService{
    @Autowired
    private RegisterRepository registerRepo;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private RegisterMapper regMapper;
    @Autowired
    private PasswordService passwordService;
    @Override
    public List<String> registerUsers(RegisterDto regDto) {
        List<String> saveUsers = new ArrayList<>();
        String encryptedPassword = passwordService.encryptPassword(regDto.getPassword());
        regDto.setFirstName(regDto.getFirstName().toUpperCase());
        regDto.setLastName(regDto.getLastName().toUpperCase());
        regDto.setEmail(regDto.getEmail().toLowerCase());
        regDto.setPassword(encryptedPassword);
        regDto.setConfirmPassword(encryptedPassword);
        RegisterEntity regEntity = regMapper.dtoToEntity(regDto);
        boolean isRoleExist = roleRepo.existsById(regDto.getRolesAssigned().getId());
        if(isRoleExist){
            registerRepo.save(regEntity);
            saveUsers.add("User Registered Successfully");
        }else{
            saveUsers.add(regDto.getRolesAssigned().getId() + " not found");
        }
        return saveUsers;
    }
}