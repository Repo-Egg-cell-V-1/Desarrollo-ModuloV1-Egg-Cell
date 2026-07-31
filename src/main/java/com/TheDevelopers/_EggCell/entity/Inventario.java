package com.TheDevelopers._EggCell.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Inventario")
@Data
public class Inventario {
    
    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "id_lote")
    private int idLote;
    
    @Column(name = "cantidad_B")
    private int cantidadB;

    @Column(name = "cantidad_A")
    private int cantidadA;

    @Column(name = "cantidad_AA")
    private int cantidadAA;

    @Column(name = "cantidad_AAA")
    private int cantidadAAA;

    @Column(name = "cantidad_JUMBO")
    private int cantidadJumbo;  

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @PrePersist
    public void prePersist() {

        fecha = LocalDateTime.now();

    }
}
