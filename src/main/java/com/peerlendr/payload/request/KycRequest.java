package com.peerlendr.payload.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KycRequest {

    @NotBlank(message = "User uniqueId is required")
    private String uniqueId;

    @NotBlank(message = "Document type is required")
    private String documentType;

    @NotBlank(message = "Document number is required")
    private String documentNumber;

    @Pattern(regexp = "\\d{10}", message = "BVN must be a 10-digit number")
    private String bvn;

    @Future(message = "Expiry date must be in the future")
    private Date expiryDate;

    @NotBlank(message = "Nationality is required")
    private String nationality;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Occupation is required")
    private String occupation;

    @NotBlank(message = "Marital status is required")
    private String maritalStatus;
}
