package com.eggcell.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record LoteDTO(
    Long id,
    @NotBlank(message = "El código es obligatorio") String codigo,
    @NotNull(message = "La fecha de ingreso es obligatoria") LocalDate fechaIngreso,
    @NotNull @Min(value = 1, message = "Debe registrar al menos 1 ave") Integer cantidadAvesIniciales,
    @Min(value = 0) Integer cantidadAvesActivas,
    String raza,
    String estado // "ACTIVO", "INACTIVO"
) {}
