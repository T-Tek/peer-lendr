package com.peerlendr.utils;

import com.peerlendr.enums.ResponseCodeAndMessage;
import com.peerlendr.payload.response.Response;
import lombok.Getter;

import java.time.Year;

@Getter
public class UserUtils {
    public static Response getUserResponseCodeAndMessage(ResponseCodeAndMessage responseCodeAndMessage, Object data){
        return Response.builder()
                .responseCode(responseCodeAndMessage.code)
                .message(responseCodeAndMessage.message)
                .data(data)
                .build();
    }

    public static String generateUniqueId(){
        Year currentYear = Year.now();
        int min = 100000;
        int max = 999999;

        int randNumber = (int) (Math.random() * (max - min + 1) + min);

        int yearValue = currentYear.getValue();

        String year = String.valueOf(yearValue);
        String randomNumber = String.valueOf(randNumber);
        String prefix = "PLDR";

        return prefix + year + randomNumber;
    }
}
