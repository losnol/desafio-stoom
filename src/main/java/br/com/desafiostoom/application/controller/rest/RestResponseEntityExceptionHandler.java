package br.com.desafiostoom.application.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleException(Exception e) {
        return ResponseEntity.badRequest().body(String.format("Error processing requisition.\nOriginal Message: \"%s\"", e.getMessage()));
    }

}
