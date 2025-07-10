package com.prueba3.carrito.service;



import com.prueba3.carrito.dto.CarritoDTO;
import com.prueba3.carrito.dto.ItemCarritoDTO;
import com.prueba3.carrito.model.Carrito;
import com.prueba3.carrito.model.ItemCarrito;
import com.prueba3.carrito.repository.CarritoRepository;
import com.prueba3.carrito.repository.ItemCarritoRepository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarritoService {
    private final CarritoRepository carritoRepository;
    @Autowired
    private ItemCarritoRepository itemCarritoRepository;

    public CarritoService(CarritoRepository carritoRepository, ItemCarritoRepository itemCarritoRepository) {
        this.carritoRepository = carritoRepository;
        this.itemCarritoRepository = itemCarritoRepository;
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

    @Transactional
    public CarritoDTO actualizar(Long id, CarritoDTO dto) {
        Carrito carrito = carritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        carrito.setUsuarioId(dto.getUsuarioId());

        // Asocia el carrito a cada ítem nuevo
        List<ItemCarrito> itemsNuevos = dto.getItems().stream()
            .map(itemDto -> {
                ItemCarrito item = toEntity(itemDto);
                item.setCarrito(carrito);
                return item;
            })
            .collect(Collectors.toList());
        // Elimina explícitamente los ítems antiguos de la base de datos
        itemCarritoRepository.deleteByCarritoId(carrito.getId());

        carrito.getItems().clear();
        carrito.getItems().addAll(itemsNuevos);

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
    public CarritoDTO agregarItem(Long carritoId, ItemCarritoDTO itemDto) {
    // 1. Buscar el carrito
    Carrito carrito = carritoRepository.findById(carritoId)
            .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
    // 2. Crear el nuevo ítem
        ItemCarrito item = toEntity(itemDto);

        // 3. Asociar el carrito al ítem (¡esto es clave!)
        item.setCarrito(carrito);

        // 4. Agregar el ítem a la lista
        carrito.getItems().add(item);

        // 5. Guardar el carrito actualizado
        Carrito actualizado = carritoRepository.save(carrito);

        // 6. Devolver el DTO actualizado
        return toDTO(actualizado);
    }
}