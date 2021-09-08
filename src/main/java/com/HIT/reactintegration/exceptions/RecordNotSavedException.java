package com.HIT.reactintegration.exceptions;

public class RecordNotSavedException extends RuntimeException {

    public RecordNotSavedException() {
        super("Record could not be saved.");
    }

    public RecordNotSavedException(String message) {
        super(message);
    }
}
