package com.social.dailylink.exception.handlers;

import com.social.dailylink.global.GlobalStrings;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {AccessDeniedException.class})
    protected ResponseEntity<Object> handleAccessDenied(RuntimeException exception, WebRequest request) {
        return handleExceptionInternal(exception, GlobalStrings.EXCEPTION_ACCESS_DENIED,
                new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        logger.error("400 Status Code", ex);
        return handleExceptionInternal(ex, GlobalStrings.EXCEPTION_BAD_REQUEST,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
