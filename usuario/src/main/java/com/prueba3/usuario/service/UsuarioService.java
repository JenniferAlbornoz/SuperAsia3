package com.prueba3.usuario.service;


import com.prueba3.usuario.dto.UsuarioDTO;
import com.prueba3.usuario.model.Usuario;
import com.prueba3.usuario.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDTO crear(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(dto, usuario);
        usuario.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        Usuario guardado = usuarioRepository.save(usuario);
        UsuarioDTO result = new UsuarioDTO();
        BeanUtils.copyProperties(guardado, result);
        return result;
    }

    public Optional<UsuarioDTO> obtenerPorId(Long id) {
        return usuarioRepository.findById(id).map(this::toDTO);
    }

    public List<UsuarioDTO> obtenerTodos() {
        return usuarioRepository.findAll().stream().map(this::toDTO).toList();
    }

    public UsuarioDTO actualizar(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        BeanUtils.copyProperties(dto, usuario, "id");
        Usuario actualizado = usuarioRepository.save(usuario);
        return toDTO(actualizado);
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Optional<UsuarioDTO> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email).map(this::toDTO);
    }

    private UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        BeanUtils.copyProperties(usuario, dto);
        return dto;
    }
}