package com.HIT.reactintegration.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("Catching " + ex.getClass().getSimpleName() + " exception class");
        HashMap<String, String> responseBody = new HashMap<>();

        switch (ex.getClass().getSimpleName()){
            case "MissingServletRequestPartException":
                log.info("Casting " + MissingServletRequestPartException.class.getSimpleName());
                MissingServletRequestPartException exception = (MissingServletRequestPartException) ex;
                responseBody.put("Missing part", exception.getRequestPartName());
        }

        if (responseBody.isEmpty()) responseBody.put("Exception",ex.getMessage());
        return super.handleExceptionInternal(ex, responseBody, headers, status, request);
    }
}
