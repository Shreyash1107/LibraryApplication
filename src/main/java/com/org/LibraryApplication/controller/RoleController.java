package com.org.LibraryApplication.controller;

import com.org.LibraryApplication.dto.RoleDto;
import com.org.LibraryApplication.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @PostMapping("/saveRoles")
    public ResponseEntity<List<String>> saveRoles(@RequestBody RoleDto roleDto){
        List<String> saveRoles = roleService.saveRoles(roleDto);
        if(saveRoles.get(0).contains("Saved Successfully")){
            return new ResponseEntity<>(saveRoles,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(saveRoles,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getRoles/{id}")
    public ResponseEntity<List<RoleDto>> getRolesById(@PathVariable Integer id){
        List<RoleDto> roleById = roleService.getRoles(id);
        if(roleById!=null){
            return new ResponseEntity<>(roleById,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
}