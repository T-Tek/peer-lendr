package com.peerlendr.repository;

import com.peerlendr.entity.Investor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestorRepository extends JpaRepository<Investor, Long> {
    boolean existsByEmail(String email);
}
