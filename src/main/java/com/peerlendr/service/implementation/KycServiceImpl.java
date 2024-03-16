package com.peerlendr.service.implementation;

import com.peerlendr.entity.Admin;
import com.peerlendr.entity.Borrower;
import com.peerlendr.entity.Investor;
import com.peerlendr.entity.KycData;
import com.peerlendr.exceptions.DuplicateEmailException;
import com.peerlendr.exceptions.ResourceNotFoundException;
import com.peerlendr.payload.request.KycRequest;
import com.peerlendr.payload.response.KycResponse;
import com.peerlendr.repository.AdminRepository;
import com.peerlendr.repository.BorrowerRepository;
import com.peerlendr.repository.InvestorRepository;
import com.peerlendr.repository.KycRepository;
import com.peerlendr.service.AdminService;
import com.peerlendr.service.BorrowerService;
import com.peerlendr.service.InvestorService;
import com.peerlendr.service.KycService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KycServiceImpl implements KycService {


    private static final Logger logger = LoggerFactory.getLogger(KycServiceImpl.class);

    private final KycRepository kycRepository;
    private final AdminRepository adminRepository;
    private final BorrowerRepository borrowerRepository;
    private final InvestorRepository investorRepository;

    @Override
    public KycResponse submitKycDocuments(KycRequest request) {
        String documentNumber = request.getDocumentNumber();
        String message = "Document with the number: " + documentNumber + " already exists";

        logger.info("Received request to submit KYC documents for document number: {}", documentNumber);

        if (isDocumentNumberExists(documentNumber)) {
            logger.error("Document with document number: {} already exists", documentNumber);
            throw new DuplicateEmailException(message);
        }

        if (!isUniqueIdValid(request.getUniqueId())) {
            logger.error("Invalid uniqueId: {}", request.getUniqueId());
            throw new ResourceNotFoundException("Invalid uniqueId");
        }

        KycData newData = KycData.builder()
                .uniqueId(request.getUniqueId())
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


    private boolean isUniqueIdValid(String uniqueId) {

        Optional<Admin> adminOptional = adminRepository.findByUniqueId(uniqueId);
        if (adminOptional.isPresent()) {
            return true;
        }

        Optional<Borrower> borrowerOptional = borrowerRepository.findByUniqueId(uniqueId);
        if (borrowerOptional.isPresent()) {
            return true;
        }

        Optional<Investor> investorOptional = investorRepository.findByUniqueId(uniqueId);
        return investorOptional.isPresent();
    }
}
