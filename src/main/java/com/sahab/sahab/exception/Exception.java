package com.sahab.sahab.exception;


import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class Exception extends RuntimeException{

    public Exception(String msg){

        super(msg);
    }
}
