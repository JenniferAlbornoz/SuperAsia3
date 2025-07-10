package com.prueba3.usuario.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba3.usuario.dto.UsuarioDTO;
import com.prueba3.usuario.service.UsuarioService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UsuarioService usuarioService;

    @MockBean
        private UsuarioService usuarioService;

    @Test
    void crearUsuario_emailVacio_retorna400() throws Exception {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre("Juan Perez");
        dto.setEmail(""); // Email vacío
        dto.setPassword("123456");

        mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void crearUsuario_nombreVacio_retorna400() throws Exception {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre(""); // Nombre vacío
        dto.setEmail("test@mail.com");
        dto.setPassword("123456");

        mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void crearUsuario_emailInvalido_retorna400() throws Exception {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre("Juan Perez");
        dto.setEmail("no-es-un-email"); // Email inválido
        dto.setPassword("123456");

        mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void obtenerPorId_notFound() throws Exception {
        mockMvc.perform(get("/api/usuarios/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}