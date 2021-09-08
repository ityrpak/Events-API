package com.HIT.reactintegration.utils;

import com.HIT.reactintegration.exceptions.NoRecordsFoundException;
import com.HIT.reactintegration.exceptions.RecordNotSavedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    @ExceptionHandler(RecordNotSavedException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(RecordNotSavedException ex){
        HttpStatus conflict = HttpStatus.CONFLICT;
        HashMap<String, String> exceptionBodyResponse = new HashMap<>();
        exceptionBodyResponse.put("Exception message", ex.getMessage());
        log.info("Returning " + conflict.toString() + " status with " + exceptionBodyResponse + " body");
        return new ResponseEntity<Object>(exceptionBodyResponse, conflict);
    }

    @ExceptionHandler(NoRecordsFoundException.class)
    protected ResponseEntity<Object> handleNoRecordsFoundException(NoRecordsFoundException ex){
        HttpStatus status = HttpStatus.OK;
        HashMap<String, String> exceptionBodyResponse = new HashMap<>();
        exceptionBodyResponse.put("Exception message", ex.getMessage());
        log.info("Returning " + status.toString() + " status with " + exceptionBodyResponse + " body");
        return new ResponseEntity<Object>(exceptionBodyResponse, status);
    }

}
