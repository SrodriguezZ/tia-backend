package com.proyect.base.persistencia.dto.Producto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductoRequest  (Long id,
        @NotBlank
         String nombreProducto,
        @NotNull
        Long stock
){
}
