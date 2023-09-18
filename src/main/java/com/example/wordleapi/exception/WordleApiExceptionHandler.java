package com.example.wordleapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class WordleApiExceptionHandler {

    @ExceptionHandler(InvalidParameterFormatException.class)
    public ResponseEntity<WordleErrorResponse> handleInvalidParameterFormatException(InvalidParameterFormatException exc) {
        WordleErrorResponse error = new WordleErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setDate(LocalDate.now());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<WordleErrorResponse> handleException(Exception exc) {
        WordleErrorResponse error = new WordleErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setDate(LocalDate.now());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
