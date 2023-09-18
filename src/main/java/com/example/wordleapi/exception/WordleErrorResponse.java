package com.example.wordleapi.exception;

import java.time.LocalDate;

public class WordleErrorResponse {

    private int status;
    private String message;
    private LocalDate date;

    public WordleErrorResponse() {
    }

    public WordleErrorResponse(int status, String message, LocalDate date) {
        this.status = status;
        this.message = message;
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
