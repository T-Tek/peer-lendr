package com.peerlendr.service;

import com.peerlendr.payload.request.AdminRequest;
import com.peerlendr.payload.request.BorrowerRequest;
import com.peerlendr.payload.response.UserResponse;

public interface AdminService {
    UserResponse createAdmin(AdminRequest adminRequest);
}
