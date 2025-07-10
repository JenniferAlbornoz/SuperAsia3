package com.prueba3.carrito.repository;

import com.prueba3.carrito.model.ItemCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemCarritoRepository extends JpaRepository<ItemCarrito, Long> {

    // Elimina todos los ítems de un carrito específico
    @Modifying
    @Query("DELETE FROM ItemCarrito i WHERE i.carrito.id = :carritoId")
    void deleteByCarritoId(@Param("carritoId") Long carritoId);

    // Busca todos los ítems de un carrito específico
    List<ItemCarrito> findByCarritoId(Long carritoId);
}