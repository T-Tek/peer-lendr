package com.peerlendr.service;

import com.peerlendr.payload.request.KycRequest;
import com.peerlendr.payload.response.KycResponse;
import com.peerlendr.payload.response.Response;
import org.springframework.stereotype.Service;

@Service
public interface KycService {
    KycResponse submitKycDocuments(KycRequest request);
}
