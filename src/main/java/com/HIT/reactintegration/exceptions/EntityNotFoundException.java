package com.HIT.reactintegration.exceptions;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String message) {
        super(message + " was not found");
    }

    public EntityNotFoundException(String entity, String name) {
        super(entity + " named " + name + " was not found");
    }
}
