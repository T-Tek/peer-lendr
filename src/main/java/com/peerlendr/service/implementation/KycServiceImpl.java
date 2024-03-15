package com.peerlendr.service.implementation;

import com.peerlendr.entity.KycData;
import com.peerlendr.exceptions.DuplicateEmailException;
import com.peerlendr.payload.request.KycRequest;
import com.peerlendr.payload.response.KycResponse;
import com.peerlendr.repository.KycRepository;
import com.peerlendr.service.KycService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KycServiceImpl implements KycService {

    private static final Logger logger = LoggerFactory.getLogger(KycServiceImpl.class);

    private final KycRepository kycRepository;

    @Override
    public KycResponse submitKycDocuments(KycRequest request) {
        String documentNumber = request.getDocumentNumber();
        String message = "Document with the number: " + documentNumber + " already exists";

        logger.info("Received request to submit KYC documents for document number: {}", documentNumber);

        if (isDocumentNumberExists(documentNumber)) {
            logger.error("Document with document number: {} already exists", documentNumber);
            throw new DuplicateEmailException(message);
        }

        KycData newData = KycData.builder()
                .idNumber(request.getIdNumber())
                .documentType(request.getDocumentType())
                .documentNumber(request.getDocumentNumber())
                .bvn(request.getBvn())
                .expiryDate(request.getExpiryDate())
                .nationality(request.getNationality())
                .gender(request.getGender())
                .occupation(request.getOccupation())
                .maritalStatus(request.getMaritalStatus())
                .build();

        var savedData = kycRepository.save(newData);

        KycResponse response = convertToDTO(savedData);

        logger.info("KYC documents submitted successfully for document number: {}", documentNumber);
        return response;
    }

    private boolean isDocumentNumberExists(String documentNumber) {
        return kycRepository.existsByDocumentNumber(documentNumber);
    }

    private KycResponse convertToDTO(KycData kycData) {
        KycResponse response = new KycResponse();
        BeanUtils.copyProperties(kycData, response);
        return response;
    }
}
