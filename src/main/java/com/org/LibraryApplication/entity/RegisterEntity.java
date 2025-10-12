package com.org.LibraryApplication.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "register")
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterEntity {
    @Id
    @Column(name = "rid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rid;
    @Column(name = "firstname",nullable = false,length = 30)
    private String firstName;
    @Column(name = "lastname",nullable = false,length = 30)
    private String lastName;
    @Column(name = "email",nullable = false,length = 50)
    private String email;
    @Column(name = "contact",nullable = false,length = 12)
    private String contact;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "confirmpassword",nullable = false)
    private String confirmPassword;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleid",nullable = false)
    private RoleEntity rolesAssigned;
}