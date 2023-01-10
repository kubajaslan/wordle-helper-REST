package com.example.wordleapi.exception;

public class MalformedRequestParamException extends RuntimeException{
    public MalformedRequestParamException(String message) {
        super(message);
    }

    public MalformedRequestParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public MalformedRequestParamException(Throwable cause) {
        super(cause);
    }
}
