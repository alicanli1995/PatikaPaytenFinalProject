package com.patikafinal.graduationproject.exception;

import lombok.Getter;

@Getter
public class MemberNotFoundException extends RuntimeException{

    private final ExceptionType exceptionType;
    private String details;

    public MemberNotFoundException(ExceptionType exceptionType, String details) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
        this.details = details;
    }
    public MemberNotFoundException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }
}
