package com.prueba3.inventario.repository;


import com.prueba3.inventario.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    List<Inventario> findByNombreContainingIgnoreCase(String nombre);
    Optional<Inventario> findByProductoId(Long productoId);
}