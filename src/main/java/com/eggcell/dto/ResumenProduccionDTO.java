package com.eggcell.dto;

public record ResumenProduccionDTO(
    Long loteId,
    String codigoLote,
    Integer totalAvesActivas,
    Integer totalHuevosRecolectados,
    Double porcentajePostura,
    Double totalAlimentoKg
) {}
