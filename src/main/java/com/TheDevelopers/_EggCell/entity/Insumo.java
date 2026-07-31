package com.TheDevelopers._EggCell.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Insumo")
@Data
public class Insumo {
    
    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    
    @Column(name = "nombre")
    private String nombre; 

    @Column(name = "fecha_compra")
    private LocalDate fechaCompra;

    @Column(name = "id_lote_aplicacion")
    private int idLoteAplicacion; 
    
    @PrePersist
    public void prePersist() {

        fechaCompra = LocalDate.now();

    }
}
