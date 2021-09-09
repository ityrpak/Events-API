package com.HIT.reactintegration.exceptions;

public class EventNotSavedException extends RuntimeException {

    public EventNotSavedException() {
        super("Event could not be saved.");
    }

    public EventNotSavedException(String message) {
        super(message);
    }
}
