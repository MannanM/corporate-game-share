package com.mannanlive.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class DefaultControllerAdvice {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorResponse> conflict(HttpServletRequest req, HttpClientErrorException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), ex.getStatusCode());
    }

    public class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}