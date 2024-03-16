package com.peerlendr.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class KycData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uniqueId;
    private String documentType;
    private String documentNumber;
    private String bvn;
    private Date expiryDate;
    private String nationality;
    private String gender;
    private String occupation;
    private String maritalStatus;
}

