package com.peerlendr.repository;

import com.peerlendr.entity.Investor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvestorRepository extends JpaRepository<Investor, Long> {
    boolean existsByEmail(String email);

    Optional<Investor> findByUniqueId(String uniqueId);

}
