package com.example.communitymember.config.controller;

import com.example.communitymember.config.exeptions.CommunitySaveException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomRestControllerAdivice {
    @ExceptionHandler(CommunitySaveException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String CommunitySaveExceptionHandler(CommunitySaveException communitySaveException){
        return communitySaveException.getMessage();
    }
}
