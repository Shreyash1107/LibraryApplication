package com.org.LibraryApplication.dto;

import lombok.*;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private int id;
    private String roles;
    private String description;
}
