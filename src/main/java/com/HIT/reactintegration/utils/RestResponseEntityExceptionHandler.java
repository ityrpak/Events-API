package com.HIT.reactintegration.utils;

import com.HIT.reactintegration.dtos.exceptiondto.ExceptionDTO;
import com.HIT.reactintegration.exceptions.EntityNotFoundException;
import com.HIT.reactintegration.exceptions.NoRecordsFoundException;
import com.HIT.reactintegration.exceptions.EventNotSavedException;
import com.HIT.reactintegration.dtos.responsesdto.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String EXCEPTION_MESSAGE_PLACEHOLDER = "Exception message";

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("Catching " + ex.getClass().getSimpleName() + " exception class");
        HashMap<String, String> errorsMap = new HashMap<>();

        switch (ex.getClass().getSimpleName()){
            case "MissingServletRequestPartException":
                log.info("Casting " + MissingServletRequestPartException.class.getSimpleName());
                MissingServletRequestPartException exception1 = (MissingServletRequestPartException) ex;
                errorsMap.put("Missing part", exception1.getRequestPartName());

            case "MethodArgumentNotValidException":
                log.info("Casting " + MethodArgumentNotValidException.class.getSimpleName());
                MethodArgumentNotValidException exception2 = (MethodArgumentNotValidException) ex;
                errorsMap.put("Invalid argument", exception2.getParameter().getParameterName());
        }

        if (errorsMap.isEmpty()) {
            errorsMap.put("Exception", ex.getClass().getSimpleName());
            errorsMap.put(EXCEPTION_MESSAGE_PLACEHOLDER, ex.getMessage());
        };
        Map responseBody = createErrorResponse(errorsMap, status);
        return super.handleExceptionInternal(ex, responseBody, headers, status, request);
    }

    @ExceptionHandler(EventNotSavedException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(EventNotSavedException ex){
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        ErrorResponseDTO exceptionBodyResponse = createErrorResponse(ex, httpStatus);
        log.info(returnStatus(exceptionBodyResponse));
        return new ResponseEntity<Object>(exceptionBodyResponse, httpStatus);
    }

    @ExceptionHandler(NoRecordsFoundException.class)
    protected ResponseEntity<Object> handleNoRecordsFoundException(NoRecordsFoundException ex){
        HttpStatus httpStatus = HttpStatus.OK;
        ErrorResponseDTO exceptionBodyResponse = createErrorResponse(ex, httpStatus);
        log.info(returnStatus(exceptionBodyResponse));
        return new ResponseEntity<Object>(exceptionBodyResponse, httpStatus);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex){
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        ErrorResponseDTO exceptionBodyResponse = createErrorResponse(ex, httpStatus);
        log.info(returnStatus(exceptionBodyResponse));
        return new ResponseEntity<Object>(exceptionBodyResponse, httpStatus);
    }

    private String returnStatus(ErrorResponseDTO err) {
        return "Returning " + err.getCode() + " " + err.getStatus() + " status with " + err.getExceptions() + " body";
    }

    private ErrorResponseDTO createErrorResponse(Exception ex, HttpStatus httpStatus) {
        return ErrorResponseDTO.builder()
                .code(httpStatus.value())
                .status(httpStatus.getReasonPhrase())
                .exceptions(ExceptionDTO.builder()
                        .exceptionName(ex.getClass().getSimpleName())
                        .exceptionMessage(ex.getMessage())
                        .build())
                .build();
    }

    private Map<String, Object> createErrorResponse(Map errors, HttpStatus httpStatus) {
        return Map.of("code", httpStatus.value(),"status", httpStatus.getReasonPhrase(), "error", errors);
    }


}
