package com.peerlendr.repository;

import com.peerlendr.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    boolean existsByEmail(String email);
    Optional<Admin> findByUniqueId(String uniqueId);

}
