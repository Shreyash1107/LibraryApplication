package com.org.LibraryApplication.mapper;

import com.org.LibraryApplication.dto.RoleDto;
import com.org.LibraryApplication.entity.RoleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class RoleMapper {
    @Autowired
    private ModelMapper modelMapper;
    public RoleEntity dtoToToEntity(RoleDto roleDto){

        return modelMapper.map(roleDto, RoleEntity.class);
    }
    public RoleDto entityToDto(RoleEntity roleEntity){
        return modelMapper.map(roleEntity, RoleDto.class);
    }
}