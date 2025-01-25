package com.proyect.base.persistencia.dto.Locales;

import jakarta.validation.constraints.NotBlank;

public record LocalesRequest(
        Long id,
        @NotBlank
         String nombreLocal,
         String direccion,
         String telefono
) {
}
