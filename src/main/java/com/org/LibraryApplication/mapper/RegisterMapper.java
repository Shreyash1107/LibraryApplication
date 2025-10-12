package com.org.LibraryApplication.mapper;

import com.org.LibraryApplication.dto.RegisterDto;
import com.org.LibraryApplication.entity.RegisterEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterMapper {
    @Autowired
    private ModelMapper modelMap;

    public RegisterEntity dtoToEntity(RegisterDto regDto) {
        return modelMap.map(regDto, RegisterEntity.class);
    }

    public RegisterDto entityToDto(RegisterEntity regEntity) {
        return modelMap.map(regEntity, RegisterDto.class);
    }
}