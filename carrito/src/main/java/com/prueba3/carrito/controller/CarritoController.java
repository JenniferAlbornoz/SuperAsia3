package com.prueba3.carrito.controller;



import com.prueba3.carrito.dto.CarritoDTO;
import com.prueba3.carrito.service.CarritoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carritos")
public class CarritoController {
    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @PostMapping
    public ResponseEntity<CarritoDTO> crear(@Valid @RequestBody CarritoDTO dto) {
        return ResponseEntity.ok(carritoService.crear(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarritoDTO> obtenerPorId(@PathVariable Long id) {
        return carritoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CarritoDTO> obtenerPorUsuarioId(@PathVariable Long usuarioId) {
        return carritoService.obtenerPorUsuarioId(usuarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<CarritoDTO> obtenerTodos() {
        return carritoService.obtenerTodos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarritoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody CarritoDTO dto) {
        return ResponseEntity.ok(carritoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        carritoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}