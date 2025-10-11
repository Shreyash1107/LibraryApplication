package com.org.LibraryApplication.controller;

import com.org.LibraryApplication.dto.RoleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @PostMapping("/saveRoles")
    public ResponseEntity<List<String>> saveRoles(@RequestBody RoleDto roleDto){
        return null;
    }
}