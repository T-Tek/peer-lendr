package com.peerlendr.entity;

import com.peerlendr.enums.UserType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "borrowers")
public class Borrower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;
    private String uniqueId;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private String password;
    private Date dateOfBirth;

    @Column(unique = true)
    private String phoneNumber;

    private String address;

    @CreationTimestamp
    private LocalDateTime dateTime;
}

