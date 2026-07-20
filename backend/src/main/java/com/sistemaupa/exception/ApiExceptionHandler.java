package com.sistemaupa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> tratarRegra(IllegalArgumentException exception) {
        return Map.of(
                "timestamp", LocalDateTime.now(),
                "status", 400,
                "message", exception.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> tratarValidacao(
            MethodArgumentNotValidException exception
    ) {
        return Map.of(
                "timestamp", LocalDateTime.now(),
                "status", 400,
                "message", "Dados inválidos.",
                "errors", exception.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(error -> error.getField() + ": " + error.getDefaultMessage())
                        .toList()
        );
    }
}
