package com.HIT.reactintegration.exceptions;

public class NoRecordsFoundException extends RuntimeException{

    public NoRecordsFoundException(String message) {
        super("No records were found for: " + message);
    }
}
