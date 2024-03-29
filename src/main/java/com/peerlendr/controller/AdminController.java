package com.peerlendr.controller;

import com.peerlendr.enums.ResponseCodeAndMessage;
import com.peerlendr.payload.request.AdminRequest;
import com.peerlendr.payload.response.Response;
import com.peerlendr.service.AdminService;
import com.peerlendr.utils.UserUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {

    public final AdminService adminService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createUser(@Valid @RequestBody AdminRequest request){
        var data = adminService.createAdmin(request);
        return UserUtils.getUserResponseCodeAndMessage(ResponseCodeAndMessage.SUCCESS, data);
    }
}
