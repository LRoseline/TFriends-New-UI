package com.tfriends.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class BoardForbiddenException extends RuntimeException {
    public BoardForbiddenException(String message) {
        super("Not Found this hash.");
    }
}
