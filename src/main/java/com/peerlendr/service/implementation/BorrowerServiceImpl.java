package com.peerlendr.service.implementation;

import com.peerlendr.entity.Borrower;
import com.peerlendr.enums.UserType;
import com.peerlendr.payload.request.BorrowerRequest;
import com.peerlendr.payload.response.UserResponse;
import com.peerlendr.repository.BorrowerRepository;
import com.peerlendr.service.BorrowerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

@Service
@RequiredArgsConstructor
public class BorrowerServiceImpl implements BorrowerService {

    private static final Logger logger = LoggerFactory.getLogger(BorrowerServiceImpl.class);

    private final BorrowerRepository borrowerRepository;

    @Override
    public UserResponse createBorrower(BorrowerRequest borrowerRequest) {
        logger.info("Creating new user with email {}:", borrowerRequest.getEmail());

        if (isUserCreated(borrowerRequest.getEmail())){
            throw new RuntimeException("User already exists in the data source");
        }

        // validate user type
        validateUserType(borrowerRequest.getUserType());

        Borrower newUser = Borrower.builder()
                .firstName(borrowerRequest.getFirstName())
                .lastName(borrowerRequest.getLastName())
                .email(borrowerRequest.getEmail())
                .userType(borrowerRequest.getUserType())
                .password(borrowerRequest.getPassword())
                .dateOfBirth(borrowerRequest.getDateOfBirth())
                .phoneNumber(borrowerRequest.getPhoneNumber())
                .address(borrowerRequest.getAddress())
                .build();

        logger.info("Saving new user to the database...");
        Borrower savedUser = borrowerRepository.save(newUser);

        logger.info("User created successfully.");
        return convertToDTO(savedUser);
    }

    private void validateUserType(UserType userType) {
        if (userType == null || !EnumSet.of(UserType.BORROWER, UserType.INVESTOR, UserType.ADMIN).contains(userType)) {
            logger.error("Invalid user type: {}", userType);
            throw new IllegalArgumentException("Invalid user type");
        }
    }

    private UserResponse convertToDTO(Borrower borrower) {
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(borrower, response);
        return response;
    }

    private boolean isUserCreated(String userEmail){
        return borrowerRepository.existsByEmail(userEmail);
    }
}
