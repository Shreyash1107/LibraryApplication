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
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private RegisterRepository registerRepo;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private RegisterMapper regMapper;
    @Autowired
    private PasswordService passwordService;
    @Autowired
    private ValidationService validService;
    @Autowired
    private EmailService emailService;

    @Override
    public List<String> registerUsers(RegisterDto regDto) {
        List<String> saveUsers = new ArrayList<>();
        List<String> saveUserValidations = validService.validateUsers(regDto);
        if (!saveUserValidations.isEmpty()) {
            return saveUserValidations;
        } else {
            String encryptedPassword = passwordService.encryptPassword(regDto.getPassword());
            regDto.setFirstName(regDto.getFirstName().toUpperCase());
            regDto.setLastName(regDto.getLastName().toUpperCase());
            regDto.setEmail(regDto.getEmail().toLowerCase());
            regDto.setPassword(encryptedPassword);
            regDto.setConfirmPassword(encryptedPassword);
            RegisterEntity regEntity = regMapper.dtoToEntity(regDto);
            boolean isRoleExist = roleRepo.existsById(regDto.getRolesAssigned().getId());
            if (isRoleExist) {
                registerRepo.save(regEntity);
                emailService.sendEmailOnRegistration(regDto.getEmail(), regDto.getFirstName(), regDto.getLastName());
                saveUsers.add("User Registered Successfully");
            } else {
                saveUsers.add(regDto.getRolesAssigned().getId() + " not found");
            }
        }
        return saveUsers;
    }

    @Override
    public List<String> login(String email, String password) {
        List<String> loginErrors = validService.validateLogin(email,password);
        List<String> loginSuccess = new ArrayList<>();
        if (!loginErrors.isEmpty()) {
            return loginErrors;
        } else {
            email = email.toLowerCase();
            RegisterEntity regUser = registerRepo.findByEmail(email);
            String decryptedPassword = passwordService.decryptPassword(regUser.getPassword());
            System.out.println(decryptedPassword);
            if (regUser.getEmail().equals(email) && decryptedPassword.equals(password)) {
                if(regUser.getRolesAssigned().getRoles() == "ADMIN"){
                    loginSuccess.add("Login Success for " + regUser.getRolesAssigned().getRoles());
                }else if (regUser.getRolesAssigned().getRoles() == "STUDENT"){
                    loginSuccess.add("Login Success for " + regUser.getRolesAssigned().getRoles());
                }else{
                    loginSuccess.add("Login Success for " + regUser.getRolesAssigned().getRoles());
                }
            } else {
                loginSuccess.add("Login Failed, Invalid Login Credentials");
            }
            return loginSuccess;
        }
    }
}