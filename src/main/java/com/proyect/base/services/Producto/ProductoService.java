package com.proyect.base.services.Producto;

import com.proyect.base.config.advice.clasesexception.ResourceNotFoundException;
import com.proyect.base.persistencia.dto.Producto.ProductoRequest;
import com.proyect.base.persistencia.dto.Producto.ProductoResponse;
import com.proyect.base.persistencia.dto.select.SelectItem;
import com.proyect.base.persistencia.entity.Producto;
import com.proyect.base.presentation.interfaces.Producto.IProductoService;
import com.proyect.base.presentation.repository.IProductoRepository;
import com.proyect.base.util.MessageInfo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductoService  implements IProductoService {
    private final IProductoRepository iProductoRepository;

    public ProductoService(IProductoRepository iProductoRepository) {
        this.iProductoRepository = iProductoRepository;
    }

    @Override
    public List<ProductoResponse> getAllProducto() {
        return mapperToProduct(iProductoRepository.findAll());
    }

    @Override
    public MessageInfo createProducto(ProductoRequest productoRequest) {
        Producto producto = mapperToProduct(productoRequest);
        iProductoRepository.save(producto);
        return MessageInfo.accionCompleta("Se ha agregado con éxito");
    }

    @Override
    public MessageInfo deleteProducto(Long id) {
        Producto producto = findByProducto(id);
        producto.setActivo(false);
        iProductoRepository.save(producto);
        return MessageInfo.accionCompleta("Se ha eliminado con éxito");

    };

    @Override
    public MessageInfo updateProducto(ProductoRequest productoRequest) {
        Producto producto = findByProducto(productoRequest.id());
        producto.setNombreProducto(productoRequest.nombreProducto());
        producto.setStock(productoRequest.stock());
        iProductoRepository.save(producto);
        return MessageInfo.accionCompleta("Se ha actualizado con éxito");
    }

    @Override
    public List<SelectItem> selectItem() {
        return iProductoRepository.findAll()
                .stream().map(item -> SelectItem.builder()
                        .value(item.getId())
                        .label(item.getNombreProducto())
                        .build()).toList();
    }

    private List<ProductoResponse> mapperToProduct(List<Producto>  producto){
        return  producto.stream().filter(item -> item.getActivo()).map(
                item -> ProductoResponse.builder()
                        .id(item.getId())
                        .nombreProducto(item.getNombreProducto())
                        .stock(item.getStock())
                        .fechaCreacion(item.getFechaCreacion())
                        .build())
        .toList();
    }

    private Producto mapperToProduct(ProductoRequest productoRequest){
        return Producto.builder()
                .nombreProducto(productoRequest.nombreProducto())
                .stock(productoRequest.stock())
                .build();
    }

    private Producto findByProducto(Long id){
        return iProductoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el producto" , 404));
    }
}
