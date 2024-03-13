package com.peerlendr.payload.response;

import com.peerlendr.enums.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private String firstName;
    private UserType userType;
}
