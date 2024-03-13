package com.peerlendr.service.implementation;

import com.peerlendr.entity.Borrower;
import com.peerlendr.entity.Investor;
import com.peerlendr.enums.UserType;
import com.peerlendr.payload.request.BorrowerRequest;
import com.peerlendr.payload.request.InvestorRequest;
import com.peerlendr.payload.response.UserResponse;
import com.peerlendr.repository.BorrowerRepository;
import com.peerlendr.repository.InvestorRepository;
import com.peerlendr.service.InvestorService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

@Service
@RequiredArgsConstructor
public class InvestorServiceImpl implements InvestorService {

    private static final Logger logger = LoggerFactory.getLogger(InvestorServiceImpl.class);

    private final InvestorRepository investorRepository;

    @Override
    public UserResponse createInvestor(InvestorRequest investorRequest) {
        logger.info("Creating new user with email {}:", investorRequest.getEmail());

        if (isUserCreated(investorRequest.getEmail())){
            throw new RuntimeException("User already exists in the data source");
        }

        // validate user type
        validateUserType(investorRequest.getUserType());

        Investor newUser = Investor.builder()
                .firstName(investorRequest.getFirstName())
                .lastName(investorRequest.getLastName())
                .email(investorRequest.getEmail())
                .userType(investorRequest.getUserType())
                .password(investorRequest.getPassword())
                .dateOfBirth(investorRequest.getDateOfBirth())
                .phoneNumber(investorRequest.getPhoneNumber())
                .address(investorRequest.getAddress())
                .build();

        logger.info("Saving new user to the database...");
        Investor savedUser = investorRepository.save(newUser);

        logger.info("User created successfully.");
        return convertToDTO(savedUser);
    }

    private void validateUserType(UserType userType) {
        if (userType == null || !EnumSet.of(UserType.BORROWER, UserType.INVESTOR, UserType.ADMIN).contains(userType)) {
            logger.error("Invalid user type: {}", userType);
            throw new IllegalArgumentException("Invalid user type");
        }
    }

    private UserResponse convertToDTO(Investor investor) {
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(investor, response);
        return response;
    }

    private boolean isUserCreated(String userEmail){
        return investorRepository.existsByEmail(userEmail);
    }
}
