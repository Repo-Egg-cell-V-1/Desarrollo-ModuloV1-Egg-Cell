package com.eggcell.controller;

import com.eggcell.dto.RegistroPosturaDTO;
import com.eggcell.dto.ResumenProduccionDTO;
import com.eggcell.service.ProduccionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/produccion")
public class ProduccionController {

    private final ProduccionService produccionService;

    public ProduccionController(ProduccionService produccionService) {
        this.produccionService = produccionService;
    }

    @PostMapping
    public ResponseEntity<RegistroPosturaDTO> crear(@Valid @RequestBody RegistroPosturaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produccionService.registrarProduccion(dto));
    }

    @GetMapping("/resumen")
    public ResponseEntity<ResumenProduccionDTO> obtenerResumen(@RequestParam Long loteId) {
        return ResponseEntity.ok(produccionService.obtenerResumenLote(loteId));
    }
}
