package com.prueba3.carrito.service;



import com.prueba3.carrito.dto.CarritoDTO;
import com.prueba3.carrito.dto.ItemCarritoDTO;
import com.prueba3.carrito.model.Carrito;
import com.prueba3.carrito.model.ItemCarrito;
import com.prueba3.carrito.repository.CarritoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarritoService {
    private final CarritoRepository carritoRepository;

    public CarritoService(CarritoRepository carritoRepository) {
        this.carritoRepository = carritoRepository;
    }

    public CarritoDTO crear(CarritoDTO dto) {
        Carrito carrito = toEntity(dto);
        Carrito guardado = carritoRepository.save(carrito);
        return toDTO(guardado);
    }

    public Optional<CarritoDTO> obtenerPorId(Long id) {
        return carritoRepository.findById(id).map(this::toDTO);
    }

    public Optional<CarritoDTO> obtenerPorUsuarioId(Long usuarioId) {
        return carritoRepository.findByUsuarioId(usuarioId).map(this::toDTO);
    }

    public List<CarritoDTO> obtenerTodos() {
        return carritoRepository.findAll().stream().map(this::toDTO).toList();
    }

    public CarritoDTO actualizar(Long id, CarritoDTO dto) {
        Carrito carrito = carritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        carrito.setUsuarioId(dto.getUsuarioId());
        carrito.setItems(dto.getItems().stream().map(this::toEntity).collect(Collectors.toList()));
        Carrito actualizado = carritoRepository.save(carrito);
        return toDTO(actualizado);
    }

    public void eliminar(Long id) {
        carritoRepository.deleteById(id);
    }

    private CarritoDTO toDTO(Carrito carrito) {
        CarritoDTO dto = new CarritoDTO();
        dto.setId(carrito.getId());
        dto.setUsuarioId(carrito.getUsuarioId());
        if (carrito.getItems() != null) {
            dto.setItems(carrito.getItems().stream().map(this::toDTO).collect(Collectors.toList()));
        }
        return dto;
    }

    private ItemCarritoDTO toDTO(ItemCarrito item) {
        ItemCarritoDTO dto = new ItemCarritoDTO();
        BeanUtils.copyProperties(item, dto);
        return dto;
    }

    private Carrito toEntity(CarritoDTO dto) {
        Carrito carrito = new Carrito();
        carrito.setId(dto.getId());
        carrito.setUsuarioId(dto.getUsuarioId());
        if (dto.getItems() != null) {
            carrito.setItems(dto.getItems().stream().map(this::toEntity).collect(Collectors.toList()));
        }
        return carrito;
    }

    private ItemCarrito toEntity(ItemCarritoDTO dto) {
        ItemCarrito item = new ItemCarrito();
        BeanUtils.copyProperties(dto, item);
        return item;
    }
}