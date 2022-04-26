package com.retodev.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UbicacionBadRequestException extends RuntimeException{
    public UbicacionBadRequestException(String message) {
        super(message);
    }
}
