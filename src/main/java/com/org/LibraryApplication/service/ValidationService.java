package com.org.LibraryApplication.service;

import com.org.LibraryApplication.dto.RoleDto;

import java.util.List;

public interface ValidationService {
    public List<String> validateRoles(RoleDto roleDto);
}
