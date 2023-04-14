package com.spring.advice;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ProblemDetail onException(RuntimeException exception){
        var pd=ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400),exception.getMessage());
        return pd;
    }
}
