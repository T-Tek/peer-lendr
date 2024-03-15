package com.peerlendr.controller;

import com.peerlendr.enums.ResponseCodeAndMessage;
import com.peerlendr.payload.request.KycRequest;
import com.peerlendr.payload.response.Response;
import com.peerlendr.service.KycService;
import com.peerlendr.utils.UserUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kyc")
public class KycController {

    private final KycService kycService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Response submitKyc(@RequestBody @Valid KycRequest request){
        var data = kycService.submitKycDocuments(request);
        return UserUtils.getUserResponseCodeAndMessage(ResponseCodeAndMessage.SUCCESS, data);

    }
}
