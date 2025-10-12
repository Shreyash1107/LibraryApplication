package com.org.LibraryApplication.dto;

import com.org.LibraryApplication.entity.RoleEntity;
import lombok.*;

@Data
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    private String firstName;
    private String lastName;
    private String email;
    private String contact;
    private String password;
    private String confirmPassword;
    private RoleDto rolesAssigned;
}
