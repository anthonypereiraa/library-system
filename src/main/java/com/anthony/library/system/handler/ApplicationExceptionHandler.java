package com.anthony.library.system.handler;

import com.anthony.library.system.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleException(final BusinessException e) {

        final ErrorResponse body = new ErrorResponse
                .builder()
                .setCode(e.getErrorCode().getCode())
                .setMessage(e.getMessage())
                .build();

        return ResponseEntity.status(e.getErrorCode().getStatus() != null ?
                                     e.getErrorCode().getStatus() : BAD_REQUEST)
                .body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException e) {
        final List<ErrorResponse.ValidationError> errors = new ArrayList<>();
        e.getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    final String fieldName = ((FieldError) error).getField();
                    final String errorCode = error.getDefaultMessage();
                    errors.add(new ErrorResponse.ValidationError.builder()
                            .setField(fieldName)
                            .setCode(errorCode)
                            .setMessage(errorCode)
                            .build());
                });
        final ErrorResponse errorResponse = new ErrorResponse.builder()
                .setValidationErrors(errors)
                .build();
        return ResponseEntity.status(BAD_REQUEST).body(errorResponse);
    }
}
