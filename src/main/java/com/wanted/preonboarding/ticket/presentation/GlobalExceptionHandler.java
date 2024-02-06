package com.wanted.preonboarding.ticket.presentation;

import com.wanted.preonboarding.core.domain.response.ResponseHandler;
import com.wanted.preonboarding.core.exception.PreonboardingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PreonboardingException.class)
    public ResponseEntity<ResponseHandler<Void>> handle(final PreonboardingException e) {
        return ResponseEntity.badRequest()
                .body(
                        ResponseHandler.<Void>builder()
                                .message(e.getMessage())
                                .statusCode(HttpStatus.BAD_REQUEST)
                                .build()
                );
    }
}
