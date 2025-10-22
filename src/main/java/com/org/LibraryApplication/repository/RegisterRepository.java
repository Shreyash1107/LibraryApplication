package com.org.LibraryApplication.repository;

import com.org.LibraryApplication.entity.RegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterEntity,Integer> {
    boolean existsByEmail(String email);
    boolean existsByContact(String contact);
    boolean existsByRolesAssignedId(Integer id);
    RegisterEntity findByEmail(String email);
}