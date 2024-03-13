package com.peerlendr.service;

import com.peerlendr.payload.request.BorrowerRequest;
import com.peerlendr.payload.response.UserResponse;
import org.springframework.stereotype.Service;

@Service
public interface BorrowerService {
    UserResponse createBorrower(BorrowerRequest borrowerRequest);
}
