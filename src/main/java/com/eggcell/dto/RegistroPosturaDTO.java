package com.eggcell.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record RegistroPosturaDTO(
    Long id,
    @NotNull(message = "El ID del lote es obligatorio") Long loteId,
    @NotNull(message = "La fecha es obligatoria") LocalDate fecha,
    @NotNull @Min(value = 0, message = "La cantidad de huevos no puede ser negativa") Integer huevosComerciales,
    @Min(value = 0) Integer huevosRotos,
    @Min(value = 0, message = "La mortalidad no puede ser negativa") Integer bajasMortalidad, // añadido en Día 4
    @Min(value = 0, message = "La cantidad de insumos no puede ser negativa") Double alimentoConsumidoKg
) {}
