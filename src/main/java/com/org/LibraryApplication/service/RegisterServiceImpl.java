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
    @Override
    public List<String> registerUsers(RegisterDto regDto) {
        List<String> saveUsers = new ArrayList<>();
        String firstName = regDto.getFirstName().toUpperCase();
        String lastName = regDto.getLastName().toUpperCase();
        String email = regDto.getEmail().toLowerCase();
        regDto.setFirstName(firstName);
        regDto.setLastName(lastName);
        regDto.setEmail(email);
        RegisterEntity regEntity = regMapper.dtoToEntity(regDto);
        boolean isRoleExist = roleRepo.existsById(regDto.getRolesAssigned().getId());
        if(isRoleExist){
            registerRepo.save(regEntity);
            saveUsers.add("User Saved Successfully");
        }else{
            saveUsers.add(regDto.getRolesAssigned().getId() + " not found");
        }
        return saveUsers;
    }
}