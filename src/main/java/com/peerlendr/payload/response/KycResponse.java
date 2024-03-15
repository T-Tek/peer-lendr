package com.peerlendr.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KycResponse {
    private String idNumber;
    private String documentType;
    private String documentNumber;
    private String bvn;
    private String expiryDate;
    private String nationality;
    private String gender;
    private String occupation;
    private String maritalStatus;
}
