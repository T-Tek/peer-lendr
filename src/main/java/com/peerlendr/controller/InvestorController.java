package com.peerlendr.controller;

import com.peerlendr.enums.ResponseCodeAndMessage;
import com.peerlendr.payload.request.InvestorRequest;
import com.peerlendr.payload.response.Response;
import com.peerlendr.service.InvestorService;
import com.peerlendr.utils.UserUtils;
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
        var data = investorService.createInvestor(investorRequest);
        return UserUtils.getUserResponseCodeAndMessage(ResponseCodeAndMessage.SUCCESS, data);
    }
}
