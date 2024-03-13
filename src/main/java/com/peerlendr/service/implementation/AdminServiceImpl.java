package com.peerlendr.service.implementation;

import com.peerlendr.entity.Admin;
import com.peerlendr.enums.UserType;
import com.peerlendr.exceptions.DuplicateEmailException;
import com.peerlendr.payload.request.AdminRequest;
import com.peerlendr.payload.response.UserResponse;
import com.peerlendr.repository.AdminRepository;
import com.peerlendr.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    @Override
    public UserResponse createAdmin(AdminRequest adminRequest) {
        log.info("Creating new user with email {}:", adminRequest.getEmail());

        if (isUserCreated(adminRequest.getEmail())){
            throw new DuplicateEmailException("User with email" + " " + adminRequest.getEmail() + " " + "already exists in the data source");
        }

        // validate user type
        validateUserType(adminRequest.getUserType());

        Admin newUser = Admin.builder()
                .firstName(adminRequest.getFirstName())
                .lastName(adminRequest.getLastName())
                .email(adminRequest.getEmail())
                //.userType(adminRequest.getUserType())
                .password(adminRequest.getPassword())
                .dateOfBirth(adminRequest.getDateOfBirth())
                .phoneNumber(adminRequest.getPhoneNumber())
                .address(adminRequest.getAddress())
                .build();

        log.info("Saving new user to the database...");
        Admin admin = adminRepository.save(newUser);

        log.info("User created successfully.");
        return convertToDTO(admin);
    }

    private void validateUserType(UserType userType) {
        if (userType == null || !EnumSet.of(UserType.BORROWER, UserType.INVESTOR, UserType.ADMIN).contains(userType)) {
            log.error("Invalid user type: {}", userType);
            throw new IllegalArgumentException("Invalid user type");
        }
    }

    private UserResponse convertToDTO(Admin admin) {
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(admin, response);
        return response;
    }

    private boolean isUserCreated(String userEmail){
        return adminRepository.existsByEmail(userEmail);
    }

}

