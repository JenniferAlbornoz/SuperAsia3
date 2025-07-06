package com.prueba3.inventario.service;



import com.prueba3.inventario.dto.InventarioDTO;
import com.prueba3.inventario.model.Inventario;
import com.prueba3.inventario.repository.InventarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {
    private final InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    public InventarioDTO crear(InventarioDTO dto) {
        Inventario inventario = new Inventario();
        BeanUtils.copyProperties(dto, inventario);
        Inventario guardado = inventarioRepository.save(inventario);
        InventarioDTO result = new InventarioDTO();
        BeanUtils.copyProperties(guardado, result);
        return result;
    }

    public Optional<InventarioDTO> obtenerPorId(Long id) {
        return inventarioRepository.findById(id).map(this::toDTO);
    }

    public List<InventarioDTO> obtenerTodos() {
        return inventarioRepository.findAll().stream().map(this::toDTO).toList();
    }

    public InventarioDTO actualizar(Long id, InventarioDTO dto) {
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
        BeanUtils.copyProperties(dto, inventario, "id");
        Inventario actualizado = inventarioRepository.save(inventario);
        return toDTO(actualizado);
    }

    public void eliminar(Long id) {
        inventarioRepository.deleteById(id);
    }

    public List<InventarioDTO> buscarPorNombre(String nombre) {
        return inventarioRepository.findByNombreContainingIgnoreCase(nombre)
                .stream().map(this::toDTO).toList();
    }

    public Optional<InventarioDTO> obtenerPorProductoId(Long productoId) {
        return inventarioRepository.findByProductoId(productoId).map(this::toDTO);
    }

    private InventarioDTO toDTO(Inventario inventario) {
        InventarioDTO dto = new InventarioDTO();
        BeanUtils.copyProperties(inventario, dto);
        return dto;
    }
}