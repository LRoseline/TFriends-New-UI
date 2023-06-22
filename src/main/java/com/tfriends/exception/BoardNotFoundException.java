package com.tfriends.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BoardNotFoundException extends RuntimeException {
    public BoardNotFoundException(String message) {
        super("Not Found this hash.");
    }
}