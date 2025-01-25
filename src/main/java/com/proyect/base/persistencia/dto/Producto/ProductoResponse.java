package com.proyect.base.persistencia.dto.Producto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoResponse {

    private Long id;
    private String nombreProducto;
    private Long stock;
    private LocalDateTime fechaCreacion;
}
