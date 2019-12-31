package com.solvve.movies.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ErrorInfo {
    private final HttpStatus status;
    private final Class exceptionClass;
    private final String message;
}
