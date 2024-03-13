package com.peerlendr.controller;

import com.peerlendr.enums.ResponseCodeAndMessage;
import com.peerlendr.payload.request.InvestorRequest;
import com.peerlendr.payload.response.Response;
import com.peerlendr.payload.response.UserResponse;
import com.peerlendr.service.InvestorService;
import com.peerlendr.utils.UserResponseCodeAndMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/investor")
public class InvestorController {

    private final InvestorService investorService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createInvestor(@Valid @RequestBody InvestorRequest investorRequest){
        UserResponse data = investorService.createInvestor(investorRequest);
        return UserResponseCodeAndMessage.getUserResponseCodeAndMessage(ResponseCodeAndMessage.SUCCESS, data);
    }
}
