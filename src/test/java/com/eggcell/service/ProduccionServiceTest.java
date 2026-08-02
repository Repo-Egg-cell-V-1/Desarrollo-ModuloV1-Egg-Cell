package com.eggcell.service;

import com.eggcell.dto.RegistroPosturaDTO;
import com.eggcell.exception.BusinessException;
import com.eggcell.model.Lote;
import com.eggcell.repository.LoteRepository;
import com.eggcell.repository.RegistroPosturaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProduccionServiceTest {

    @Mock private RegistroPosturaRepository posturaRepository;
    @Mock private LoteRepository loteRepository;
    @InjectMocks private ProduccionService produccionService;

    @Test
    void debeLanzarExcepcionCuandoHuevosSuperanAvesActivas() {
        Lote lote = new Lote();
        lote.setId(1L);
        lote.setCantidadAvesActivas(100);

        when(loteRepository.findById(1L)).thenReturn(Optional.of(lote));

        // id, loteId, fecha, huevosComerciales, huevosRotos, bajasMortalidad, alimentoConsumidoKg
        RegistroPosturaDTO dto = new RegistroPosturaDTO(null, 1L, LocalDate.now(), 90, 20, 0, 10.0); // 110 huevos > 100 aves

        BusinessException ex = assertThrows(BusinessException.class,
            () -> produccionService.registrarProduccion(dto));

        assertTrue(ex.getMessage().contains("no puede superar la población"));
    }

    @Test
    void debeLanzarExcepcionCuandoInsumosSonNegativos() {
        RegistroPosturaDTO dto = new RegistroPosturaDTO(null, 1L, LocalDate.now(), 50, 0, 0, -5.0);

        BusinessException ex = assertThrows(BusinessException.class,
            () -> produccionService.registrarProduccion(dto));

        assertTrue(ex.getMessage().contains("no puede ser un valor negativo"));
    }
}
