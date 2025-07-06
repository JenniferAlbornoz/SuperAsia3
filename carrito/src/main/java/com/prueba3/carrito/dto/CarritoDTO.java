package com.prueba3.carrito.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CarritoDTO {
    private Long id;

    @NotNull(message = "El usuarioId es obligatorio")
    private Long usuarioId;

    private List<ItemCarritoDTO> items;
}