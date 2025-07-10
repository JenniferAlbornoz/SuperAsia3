package com.prueba3.carrito.service;

import com.prueba3.carrito.dto.CarritoDTO;
import com.prueba3.carrito.dto.ItemCarritoDTO;
import com.prueba3.carrito.model.Carrito;
import com.prueba3.carrito.model.ItemCarrito;
import com.prueba3.carrito.repository.CarritoRepository;
import com.prueba3.carrito.repository.ItemCarritoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

class CarritoServiceTest {

    private CarritoRepository carritoRepository;
    private ItemCarritoRepository itemCarritoRepository;
    private CarritoService carritoService;

    @BeforeEach
    void setUp() {
        carritoRepository = Mockito.mock(CarritoRepository.class);
        itemCarritoRepository = Mockito.mock(ItemCarritoRepository.class);
        carritoService = new CarritoService(carritoRepository, itemCarritoRepository);
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

    @Test
    void actualizarCarrito() {
        CarritoDTO dto = new CarritoDTO();
        dto.setUsuarioId(1L);
        ItemCarritoDTO itemDto = new ItemCarritoDTO();
        itemDto.setProductoId(2L);
        itemDto.setCantidad(3);
        dto.setItems(Collections.singletonList(itemDto));

        Carrito carritoExistente = Carrito.builder()
                .id(1L)
                .usuarioId(1L)
                .items(Collections.singletonList(
                        ItemCarrito.builder().id(1L).productoId(2L).cantidad(1).build()
                ))
                .build();

        Mockito.when(carritoRepository.findById(1L)).thenReturn(Optional.of(carritoExistente));
        Mockito.doNothing().when(itemCarritoRepository).deleteByCarritoId(anyLong());
        Mockito.when(carritoRepository.save(any(Carrito.class))).thenReturn(carritoExistente);

        CarritoDTO result = carritoService.actualizar(1L, dto);

        assertNotNull(result);
        assertEquals(1L, result.getUsuarioId());
        assertEquals(1, result.getItems().size());
        assertEquals(2L, result.getItems().get(0).getProductoId());
        assertEquals(3, result.getItems().get(0).getCantidad());
    }
}