package com.peerlendr.payload.request;

import com.peerlendr.enums.UserType;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AdminRequest {
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    //@NotBlank(message = "User type is required")
    private UserType userType;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private Date dateOfBirth;

    @NotBlank(message = "Phone number is required")

    @Size(min = 11, max = 11, message = "Phone number must be 11 digits")
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    private String address;
}
