package com.proyect.base.persistencia.dto.Locales;

import com.proyect.base.persistencia.dto.Producto.ProductoResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocalesResponse {
    private Long id;
    private String nombreLocal;
    private String direccion;
    private String telefono;
    private List<ProductoResponse> productoResponses;
}
