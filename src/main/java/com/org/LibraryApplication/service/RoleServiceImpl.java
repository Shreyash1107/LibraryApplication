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
    @Autowired
    private ValidationService validateService;
    @Override
    public List<String> saveRoles(RoleDto roleDto) {
        List<String> saveRoles = new ArrayList<>();
        List<String> errorInRoles = validateService.validateRoles(roleDto);
        String rolesToUpper = roleDto.getRoles().toUpperCase();
        String descriptionToUpper = roleDto.getDescription().toUpperCase();
        roleDto.setRoles(rolesToUpper);
        roleDto.setDescription(descriptionToUpper);
//        System.out.println(roleDto.getRoles() + " ");
        RoleEntity roleEntity = roleMapper.dtoToToEntity(roleDto);

        if(!errorInRoles.isEmpty()){
            return errorInRoles;
        }else{
            roleRepo.save(roleEntity);
            saveRoles.add("Role Saved Successfully");
            return saveRoles;
        }
    }
}