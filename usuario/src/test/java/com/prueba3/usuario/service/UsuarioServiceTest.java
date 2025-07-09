package com.prueba3.usuario.service;

import com.prueba3.usuario.dto.UsuarioDTO;
import com.prueba3.usuario.model.Usuario;
import com.prueba3.usuario.repository.UsuarioRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class UsuarioServiceTest {

    private UsuarioRepository usuarioRepository;
    private UsuarioService usuarioService;
    private Faker faker;

    @BeforeEach
    void setUp() {
        usuarioRepository = Mockito.mock(UsuarioRepository.class);
        usuarioService = new UsuarioService(usuarioRepository);
        faker = new Faker();
    }

    @Test
    void crearUsuario() {
        String nombre = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 16);

        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre(nombre);
        dto.setEmail(email);
        dto.setPassword(password);

        Usuario usuario = Usuario.builder()
                .id(1L)
                .nombre(nombre)
                .email(email)
                .password(password)
                .build();

        Mockito.when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        UsuarioDTO result = usuarioService.crear(dto);

        assertNotNull(result);
        assertEquals(nombre, result.getNombre());
        assertEquals(email, result.getEmail());
    }

    @Test
    void obtenerPorId_noExiste() {
        Mockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<UsuarioDTO> result = usuarioService.obtenerPorId(1L);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCrearUsuarioEmailVacio() {
        String nombre = faker.name().fullName();
        String email = ""; // Email vacío
        String password = faker.internet().password(8, 16);

        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre(nombre);
        dto.setEmail(email);
        dto.setPassword(password);

        // Si tu servicio lanza una excepción específica, cámbiala aquí
        assertThrows(Exception.class, () -> usuarioService.crear(dto));
    }
}