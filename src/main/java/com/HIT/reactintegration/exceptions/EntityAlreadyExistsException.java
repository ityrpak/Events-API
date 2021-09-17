package com.HIT.reactintegration.exceptions;

public class EntityAlreadyExistsException extends RuntimeException {

    public EntityAlreadyExistsException(String entity) {
        super(entity + " already exists");
    }

}
