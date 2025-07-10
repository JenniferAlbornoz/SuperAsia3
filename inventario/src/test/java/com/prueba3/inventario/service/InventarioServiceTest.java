package com.prueba3.inventario.service;



import com.prueba3.inventario.dto.InventarioDTO;
import com.prueba3.inventario.model.Inventario;
import com.prueba3.inventario.repository.InventarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class InventarioServiceTest {

    private InventarioRepository inventarioRepository;
    private InventarioService inventarioService;

    @BeforeEach
    void setUp() {
        inventarioRepository = Mockito.mock(InventarioRepository.class);
        inventarioService = new InventarioService(inventarioRepository);
    }

    @Test
    void crearInventario() {
        InventarioDTO dto = new InventarioDTO();
        dto.setNombre("Test");
        dto.setCantidad(10);
        dto.setProductoId(1L);

        Inventario inventario = Inventario.builder()
                .id(1L)
                .nombre("Test")
                .cantidad(10)
                .productoId(1L)
                .build();

        Mockito.when(inventarioRepository.save(any(Inventario.class))).thenReturn(inventario);

        InventarioDTO result = inventarioService.crear(dto);

        assertNotNull(result);
        assertEquals("Test", result.getNombre());
    }

    @Test
    void obtenerPorId_noExiste() {
        Mockito.when(inventarioRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<InventarioDTO> result = inventarioService.obtenerPorId(1L);
        assertTrue(result.isEmpty());
    }
}