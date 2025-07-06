package com.prueba3.inventario.controller;



import com.prueba3.inventario.dto.InventarioDTO;
import com.prueba3.inventario.service.InventarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {
    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @PostMapping
    public ResponseEntity<InventarioDTO> crear(@Valid @RequestBody InventarioDTO dto) {
        return ResponseEntity.ok(inventarioService.crear(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioDTO> obtenerPorId(@PathVariable Long id) {
        return inventarioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<InventarioDTO> obtenerTodos() {
        return inventarioService.obtenerTodos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioDTO> actualizar(@PathVariable Long id, @Valid @RequestBody InventarioDTO dto) {
        return ResponseEntity.ok(inventarioService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        inventarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public List<InventarioDTO> buscarPorNombre(@RequestParam String nombre) {
        return inventarioService.buscarPorNombre(nombre);
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<InventarioDTO> obtenerPorProductoId(@PathVariable Long productoId) {
        return inventarioService.obtenerPorProductoId(productoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}