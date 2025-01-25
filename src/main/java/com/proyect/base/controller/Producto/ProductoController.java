package com.proyect.base.controller.Producto;

import com.proyect.base.persistencia.dto.Producto.ProductoRequest;
import com.proyect.base.persistencia.dto.Producto.ProductoResponse;
import com.proyect.base.persistencia.dto.select.SelectItem;
import com.proyect.base.presentation.interfaces.Producto.IProductoService;
import com.proyect.base.util.MessageInfo;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/producto")
public class ProductoController {
    private final IProductoService iProductoService;

    public ProductoController(IProductoService iProductoService) {
        this.iProductoService = iProductoService;
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponse>> getProducto( ){
        return  new ResponseEntity<>(iProductoService.getAllProducto() , HttpStatus.OK);
    }

    @GetMapping("/select")
    public ResponseEntity<List<SelectItem>> getProductoSelect( ){
        return  new ResponseEntity<>(iProductoService.selectItem() , HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<MessageInfo> postProducto(@Valid @RequestBody ProductoRequest productoRequest){
        return  new ResponseEntity<>(iProductoService.createProducto(productoRequest) ,HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<MessageInfo> updateProducto(@Valid @RequestBody ProductoRequest productoRequest){
        return  new ResponseEntity<>(iProductoService.updateProducto(productoRequest) ,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageInfo> deleteProducto(@PathVariable("id")Long id){
        return  new ResponseEntity<>(iProductoService.deleteProducto(id) ,HttpStatus.OK);
    }

}
