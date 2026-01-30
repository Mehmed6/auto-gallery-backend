package com.doganmehmet.app.exception;

import lombok.Getter;

@Getter
public enum MessageType {
    NO_RECORD_FOUND("1004", "No record found"),
    GENERAL_ERROR("9999", "General error");

    private String code;
    private String message;

    MessageType(String code, String message)
    {
        this.code = code;
        this.message = message;
    }
}
