package com.orangetalents.orangetalentschallenge.handler;

import com.orangetalents.orangetalentschallenge.exception.ExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDetails> handlerMethodArgumentNotValidException(MethodArgumentNotValidException manve){
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                .title("Method Argument Not Valid, Check the Documentation")
                .details(manve.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build(), HttpStatus.BAD_REQUEST
        );
    }
}
