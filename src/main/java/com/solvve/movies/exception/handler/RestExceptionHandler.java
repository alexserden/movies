package com.solvve.movies.exception.handler;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class RestExceptionHandler  {

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex){

        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.BAD_REQUEST, ex.getClass(), ex.getMessage());
        return new ResponseEntity<>(errorInfo, new HttpHeaders(), errorInfo.getStatus());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handlerException(Exception ex){
        ResponseStatus status = AnnotatedElementUtils.findMergedAnnotation(ex.getClass(), ResponseStatus.class);
        HttpStatus httpStatus = status != null ? status.code() : HttpStatus.INTERNAL_SERVER_ERROR;

        ErrorInfo errorInfo = new ErrorInfo(httpStatus, ex.getClass(), ex.getMessage());
        return new ResponseEntity<>(errorInfo, new HttpHeaders(), httpStatus);
    }
}
