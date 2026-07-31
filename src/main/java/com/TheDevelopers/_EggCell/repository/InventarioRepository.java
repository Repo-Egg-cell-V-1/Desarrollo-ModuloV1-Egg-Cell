package com.TheDevelopers._EggCell.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TheDevelopers._EggCell.entity.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    
}
