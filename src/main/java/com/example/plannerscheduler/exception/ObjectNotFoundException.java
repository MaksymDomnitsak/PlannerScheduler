package com.example.plannerscheduler.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ObjectNotFoundException extends RuntimeException {
    String message;
    Object value;

    public ObjectNotFoundException(String message, Object value) {
        super(message + value);
        this.message = message;
        this.value = value;
    }
}
