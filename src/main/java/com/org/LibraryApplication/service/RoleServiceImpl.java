package com.org.LibraryApplication.service;

import com.org.LibraryApplication.dto.RoleDto;
import com.org.LibraryApplication.entity.RoleEntity;
import com.org.LibraryApplication.mapper.RoleMapper;
import com.org.LibraryApplication.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<String> saveRoles(RoleDto roleDto) {
        List<String> savedRoles = new ArrayList<>();
        RoleEntity roleEntity = roleMapper.dtoToToEntity(roleDto);
        roleRepo.save(roleEntity);
        savedRoles.add("Role Saved Successfully");
        return savedRoles;
    }
}