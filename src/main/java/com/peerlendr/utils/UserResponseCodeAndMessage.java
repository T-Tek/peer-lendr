package com.peerlendr.utils;

import com.peerlendr.enums.ResponseCodeAndMessage;
import com.peerlendr.payload.response.Response;
import lombok.Getter;

@Getter
public class UserResponseCodeAndMessage {
    public static Response getUserResponseCodeAndMessage(ResponseCodeAndMessage responseCodeAndMessage, Object data){
        return Response.builder()
                .responseCode(responseCodeAndMessage.code)
                .message(responseCodeAndMessage.message)
                .data(data)
                .build();
    }
}
