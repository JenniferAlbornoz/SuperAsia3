package com.prueba3.usuario.controller;


import com.prueba3.usuario.dto.UsuarioDTO;
import com.prueba3.usuario.service.UsuarioService;
import jakarta.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> crear(@Valid @RequestBody UsuarioDTO dto) {
        return ResponseEntity.ok(usuarioService.crear(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<UsuarioDTO>> obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id)
                .map(usuario -> {
                    EntityModel<UsuarioDTO> resource = EntityModel.of(usuario);

                    // Enlace a sí mismo
                    resource.add(WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(UsuarioController.class).obtenerPorId(id))
                            .withSelfRel());

                    // Enlace a la lista de usuarios
                    resource.add(WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(UsuarioController.class).obtenerTodos())
                            .withRel("usuarios"));

                    // Enlace para eliminar usuario
                    resource.add(WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(UsuarioController.class).eliminar(id))
                            .withRel("eliminar"));

                    return ResponseEntity.ok(resource);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<UsuarioDTO> obtenerTodos() {
        return usuarioService.obtenerTodos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioDTO dto) {
        return ResponseEntity.ok(usuarioService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioDTO> buscarPorEmail(@PathVariable String email) {
        return usuarioService.buscarPorEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}