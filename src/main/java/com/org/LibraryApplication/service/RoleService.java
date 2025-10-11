package com.org.LibraryApplication.service;

import com.org.LibraryApplication.dto.RoleDto;

import java.util.List;

public interface RoleService {
    public List<String> saveRoles(RoleDto roleDto);
}
