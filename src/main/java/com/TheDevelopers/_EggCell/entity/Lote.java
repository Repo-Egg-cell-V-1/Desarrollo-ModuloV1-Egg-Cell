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
@Table(name = "Lote")
@Data
public class Lote {
    
    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "raza")
    private String raza;
    
    @Column(name = "edad_mes")
    private int edadMes;
    
    @Column(name = "cantidad")
    private int cantidad;    

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;
    
    @Column(name = "galpon")
    private int galpon;

    @PrePersist
    public void prePersist() {

        fechaIngreso = LocalDate.now();

    }
}
