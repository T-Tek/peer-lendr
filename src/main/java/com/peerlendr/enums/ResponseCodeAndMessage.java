package com.peerlendr.enums;

import lombok.Getter;

//@Getter
public enum ResponseCodeAndMessage {
    SUCCESS("200", "Success"),
    BAD_REQUEST("400", "Bad Request"),
    UNAUTHORIZED("401", "Unauthorized"),
    FORBIDDEN("403", "Forbidden"),
    NOT_FOUND("404", "Not Found"),
    INTERNAL_SERVER_ERROR("500", "Internal Server Error");

    public final String code;
    public final String message;

    ResponseCodeAndMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

}


