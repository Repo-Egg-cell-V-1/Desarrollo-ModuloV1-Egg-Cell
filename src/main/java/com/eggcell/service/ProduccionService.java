package com.eggcell.service;

import com.eggcell.dto.*;
import com.eggcell.exception.BusinessException;
import com.eggcell.model.*;
import com.eggcell.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProduccionService {

    private final RegistroPosturaRepository posturaRepository;
    private final LoteRepository loteRepository;

    public ProduccionService(RegistroPosturaRepository posturaRepository, LoteRepository loteRepository) {
        this.posturaRepository = posturaRepository;
        this.loteRepository = loteRepository;
    }

    @Transactional
    public RegistroPosturaDTO registrarProduccion(RegistroPosturaDTO dto) {
        // Validación 1: Insumos no negativos
        if (dto.alimentoConsumidoKg() != null && dto.alimentoConsumidoKg() < 0) {
            throw new BusinessException("La cantidad de insumos consumidos no puede ser un valor negativo.");
        }

        Lote lote = loteRepository.findById(dto.loteId())
                .orElseThrow(() -> new BusinessException("El lote especificado no existe."));

        int totalHuevos = dto.huevosComerciales() + (dto.huevosRotos() != null ? dto.huevosRotos() : 0);

        // Validación 2: Huevos no superan la población de aves activas
        if (totalHuevos > lote.getCantidadAvesActivas()) {
            throw new BusinessException("La cantidad total de huevos (" + totalHuevos +
                ") no puede superar la población de aves activas del lote (" + lote.getCantidadAvesActivas() + ").");
        }

        RegistroPostura entity = new RegistroPostura();
        entity.setLote(lote);
        entity.setFecha(dto.fecha());
        entity.setHuevosComerciales(dto.huevosComerciales());
        entity.setHuevosRotos(dto.huevosRotos());
        entity.setAlimentoConsumidoKg(dto.alimentoConsumidoKg());
        // entity.setBajasMortalidad(dto.bajasMortalidad()); // habilitar cuando el modelo tenga el campo

        RegistroPostura guardado = posturaRepository.save(entity);
        return new RegistroPosturaDTO(
            guardado.getId(), lote.getId(), guardado.getFecha(),
            guardado.getHuevosComerciales(), guardado.getHuevosRotos(),
            dto.bajasMortalidad(), guardado.getAlimentoConsumidoKg()
        );
    }

    public ResumenProduccionDTO obtenerResumenLote(Long loteId) {
        Lote lote = loteRepository.findById(loteId)
                .orElseThrow(() -> new BusinessException("Lote no encontrado"));

        Integer totalHuevos = posturaRepository.sumHuevosByLoteId(loteId);
        Double totalAlimento = posturaRepository.sumAlimentoByLoteId(loteId);

        totalHuevos = (totalHuevos != null) ? totalHuevos : 0;
        totalAlimento = (totalAlimento != null) ? totalAlimento : 0.0;

        double posturaPct = (lote.getCantidadAvesActivas() > 0)
                ? ((double) totalHuevos / lote.getCantidadAvesActivas()) * 100 : 0.0;

        return new ResumenProduccionDTO(
            lote.getId(), lote.getCodigo(), lote.getCantidadAvesActivas(), totalHuevos, posturaPct, totalAlimento
        );
    }
}
