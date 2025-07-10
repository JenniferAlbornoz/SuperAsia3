package com.prueba3.producto.service;



import com.prueba3.producto.dto.ProductoDTO;
import com.prueba3.producto.model.Producto;
import com.prueba3.producto.repository.ProductoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Repository
@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public ProductoDTO crear(ProductoDTO dto) {
        Producto producto = new Producto();
        BeanUtils.copyProperties(dto, producto);
        Producto guardado = productoRepository.save(producto);
        ProductoDTO result = new ProductoDTO();
        BeanUtils.copyProperties(guardado, result);
        return result;
    }

    public Optional<ProductoDTO> obtenerPorId(Long id) {
        return productoRepository.findById(id).map(this::toDTO);
    }

    public List<ProductoDTO> obtenerTodos() {
        return productoRepository.findAll().stream().map(this::toDTO).toList();
    }

    public ProductoDTO actualizar(Long id, ProductoDTO dto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        BeanUtils.copyProperties(dto, producto, "id");
        Producto actualizado = productoRepository.save(producto);
        return toDTO(actualizado);
    }

    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }

    public List<ProductoDTO> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre)
                .stream().map(this::toDTO).toList();
    }

    private ProductoDTO toDTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        BeanUtils.copyProperties(producto, dto);
        return dto;
    }
}