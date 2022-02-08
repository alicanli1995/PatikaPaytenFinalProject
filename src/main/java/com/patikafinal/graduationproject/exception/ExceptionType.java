package com.patikafinal.graduationproject.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor

public enum ExceptionType {
    ACCOUNT_NOT_FOUND(1005, "The account is not in database.");

    private final Integer code;
    private final String message;

}
