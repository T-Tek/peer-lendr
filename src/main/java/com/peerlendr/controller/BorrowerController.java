package com.peerlendr.controller;

import com.peerlendr.enums.ResponseCodeAndMessage;
import com.peerlendr.payload.request.BorrowerRequest;
import com.peerlendr.payload.response.Response;
import com.peerlendr.service.BorrowerService;
import com.peerlendr.utils.UserUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/borrower")
public class BorrowerController {

    public final BorrowerService borrowerService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createBorrower(@Valid @RequestBody BorrowerRequest userRequest){
        var data = borrowerService.createBorrower(userRequest);
        return UserUtils.getUserResponseCodeAndMessage(ResponseCodeAndMessage.SUCCESS, data);
    }
}
