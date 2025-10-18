package com.org.LibraryApplication.repository;

import com.org.LibraryApplication.entity.RegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterEntity,Integer> {
    boolean existsByEmail(String email);
    boolean existsByContact(String contact);
    boolean existsByPassword(String password);
}
