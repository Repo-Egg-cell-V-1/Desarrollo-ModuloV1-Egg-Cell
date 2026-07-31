package com.TheDevelopers._EggCell.exception;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolationException(
            DataIntegrityViolationException exception,
            WebRequest webRequest) {

        String mensaje = "Error de integridad de datos en la base de datos.";
        if (exception.getCause() != null && exception.getCause().getMessage() != null) {
            if (exception.getCause().getMessage().toLowerCase().contains("foreign key")) {
                mensaje = "No se puede completar la operación: la referencia de llave foránea no es válida o el lote especificado no existe.";
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Bad Request");
        response.put("message", mensaje);
        response.put("path", webRequest.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            WebRequest webRequest) {

        Map<String, String> errores = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String nombreCampo = ((FieldError) error).getField();
            String mensaje = error.getDefaultMessage();
            errores.put(nombreCampo, mensaje);
        });

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Error de Validación");
        response.put("errors", errores);
        response.put("path", webRequest.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleTypeMismatchException(
            MethodArgumentTypeMismatchException exception,
            WebRequest webRequest) {

        String mensaje = String.format("El parámetro '%s' debe ser un tipo válido.", exception.getName());

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Tipo de Parámetro Inválido");
        response.put("message", mensaje);
        response.put("path", webRequest.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(
            Exception exception,
            WebRequest webRequest) {

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("error", "Internal Server Error");
        response.put("message", exception.getMessage());
        response.put("path", webRequest.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
