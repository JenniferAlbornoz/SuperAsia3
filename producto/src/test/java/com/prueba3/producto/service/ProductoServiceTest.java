package com.prueba3.producto.service;



import com.prueba3.producto.dto.ProductoDTO;
import com.prueba3.producto.model.Producto;
import com.prueba3.producto.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class ProductoServiceTest {

    private ProductoRepository productoRepository;
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        productoRepository = Mockito.mock(ProductoRepository.class);
        productoService = new ProductoService(productoRepository);
    }

    @Test
    void crearProducto() {
        ProductoDTO dto = new ProductoDTO();
        dto.setNombre("Test");
        dto.setPrecio(10.0);

        Producto producto = Producto.builder()
                .id(1L)
                .nombre("Test")
                .precio(10.0)
                .build();

        Mockito.when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        ProductoDTO result = productoService.crear(dto);

        assertNotNull(result);
        assertEquals("Test", result.getNombre());
    }

    @Test
    void obtenerPorId_noExiste() {
        Mockito.when(productoRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<ProductoDTO> result = productoService.obtenerPorId(1L);
        assertTrue(result.isEmpty());
    }
}