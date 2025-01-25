package com.proyect.base.presentation.interfaces.Producto;

import com.proyect.base.persistencia.dto.Locales.LocalesRequest;
import com.proyect.base.persistencia.dto.Locales.LocalesResponse;
import com.proyect.base.persistencia.dto.Producto.ProductoRequest;
import com.proyect.base.persistencia.dto.Producto.ProductoResponse;
import com.proyect.base.persistencia.dto.select.SelectItem;
import com.proyect.base.util.MessageInfo;

import java.util.List;

public interface IProductoService {
    List<ProductoResponse> getAllProducto();
    MessageInfo createProducto(ProductoRequest productoRequest);
    MessageInfo deleteProducto(Long id);
    MessageInfo updateProducto(ProductoRequest productoRequest);
        List<SelectItem> selectItem();
}
