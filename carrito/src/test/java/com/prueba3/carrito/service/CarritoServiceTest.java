package com.prueba3.carrito.service;



import com.prueba3.carrito.dto.CarritoDTO;
import com.prueba3.carrito.dto.ItemCarritoDTO;
import com.prueba3.carrito.model.Carrito;
import com.prueba3.carrito.model.ItemCarrito;
import com.prueba3.carrito.repository.CarritoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class CarritoServiceTest {

    private CarritoRepository carritoRepository;
    private CarritoService carritoService;

    @BeforeEach
    void setUp() {
        carritoRepository = Mockito.mock(CarritoRepository.class);
        carritoService = new CarritoService(carritoRepository);
    }

    @Test
    void crearCarrito() {
        CarritoDTO dto = new CarritoDTO();
        dto.setUsuarioId(1L);
        ItemCarritoDTO item = new ItemCarritoDTO();
        item.setProductoId(2L);
        item.setCantidad(3);
        dto.setItems(Collections.singletonList(item));

        Carrito carrito = Carrito.builder()
                .id(1L)
                .usuarioId(1L)
                .items(Collections.singletonList(
                        ItemCarrito.builder().id(1L).productoId(2L).cantidad(3).build()
                ))
                .build();

        Mockito.when(carritoRepository.save(any(Carrito.class))).thenReturn(carrito);

        CarritoDTO result = carritoService.crear(dto);

        assertNotNull(result);
        assertEquals(1L, result.getUsuarioId());
        assertEquals(1, result.getItems().size());
    }

    @Test
    void obtenerPorId_noExiste() {
        Mockito.when(carritoRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<CarritoDTO> result = carritoService.obtenerPorId(1L);
        assertTrue(result.isEmpty());
    }
}