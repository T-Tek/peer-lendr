package com.peerlendr.service;

import com.peerlendr.payload.request.BorrowerRequest;
import com.peerlendr.payload.request.InvestorRequest;
import com.peerlendr.payload.response.UserResponse;

public interface InvestorService {
    UserResponse createInvestor(InvestorRequest investorRequest);
}
