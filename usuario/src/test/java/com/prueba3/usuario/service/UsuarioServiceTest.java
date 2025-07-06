package com.prueba3.usuario.service;






import com.prueba3.usuario.dto.UsuarioDTO;
import com.prueba3.usuario.model.Usuario;
import com.prueba3.usuario.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class UsuarioServiceTest {

    private UsuarioRepository usuarioRepository;
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        usuarioRepository = Mockito.mock(UsuarioRepository.class);
        usuarioService = new UsuarioService(usuarioRepository);
    }

    @Test
    void crearUsuario() {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre("Test");
        dto.setEmail("test@mail.com");
        dto.setPassword("1234");

        Usuario usuario = Usuario.builder()
                .id(1L)
                .nombre("Test")
                .email("test@mail.com")
                .password("1234")
                .build();

        Mockito.when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        UsuarioDTO result = usuarioService.crear(dto);

        assertNotNull(result);
        assertEquals("Test", result.getNombre());
    }

    @Test
    void obtenerPorId_noExiste() {
        Mockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<UsuarioDTO> result = usuarioService.obtenerPorId(1L);
        assertTrue(result.isEmpty());
    }
}