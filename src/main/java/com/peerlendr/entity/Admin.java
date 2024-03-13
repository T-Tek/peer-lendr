package com.peerlendr.entity;

import com.peerlendr.enums.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;


    private String password;
    private Date dateOfBirth;

    @Column(unique = true)
    private String phoneNumber;

    private String address;
}

