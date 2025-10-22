package com.org.LibraryApplication.service;

import com.org.LibraryApplication.dto.RegisterDto;
import com.org.LibraryApplication.dto.RoleDto;

import java.util.List;

public interface ValidationService {
    List<String> validateRoles(RoleDto roleDto);
    List<String> validateUsers(RegisterDto regDto);
    List<String> validateLogin(String email, String password);
}
