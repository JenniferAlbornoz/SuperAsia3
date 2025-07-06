package com.prueba3.inventario.controller;




import com.prueba3.inventario.service.InventarioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InventarioController.class)
class InventarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private InventarioService inventarioService;

    @Test
    void obtenerPorId_notFound() throws Exception {
        Mockito.when(inventarioService.obtenerPorId(1L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/inventario/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}