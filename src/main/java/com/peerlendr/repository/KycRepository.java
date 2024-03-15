package com.peerlendr.repository;

import com.peerlendr.entity.KycData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KycRepository extends JpaRepository<KycData, Long> {
    boolean existsByDocumentNumber(String documentNumber);
}
