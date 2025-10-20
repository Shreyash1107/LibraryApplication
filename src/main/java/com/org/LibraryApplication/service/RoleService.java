package com.org.LibraryApplication.service;

import com.org.LibraryApplication.dto.RoleDto;
import java.util.List;

public interface RoleService {
    List<String> saveRoles(RoleDto roleDto);
    List<RoleDto> getRoles(Integer id);
    List<RoleDto> getRoles();
}