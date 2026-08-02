package com.eggcell.exception;

/**
 * Excepción para errores de reglas de negocio (validaciones que no son
 * de formato/tipo, sino de lógica: lote no existe, huevos excedentes, etc.)
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
