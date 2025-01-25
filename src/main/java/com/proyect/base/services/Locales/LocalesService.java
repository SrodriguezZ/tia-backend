package com.proyect.base.services.Locales;

import com.proyect.base.config.advice.clasesexception.ResourceNotFoundException;
import com.proyect.base.persistencia.dto.Locales.LocalesRequest;
import com.proyect.base.persistencia.dto.Locales.LocalesResponse;
import com.proyect.base.persistencia.dto.Producto.ProductoResponse;
import com.proyect.base.persistencia.dto.select.SelectItem;
import com.proyect.base.persistencia.entity.Locales;
import com.proyect.base.persistencia.entity.Producto;
import com.proyect.base.presentation.interfaces.Locales.ILocalesService;
import com.proyect.base.presentation.repository.ILocalesRepository;
import com.proyect.base.presentation.repository.IProductoRepository;
import com.proyect.base.util.MessageInfo;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocalesService implements ILocalesService {

    private final ILocalesRepository iLocalesRepository;
    private final IProductoRepository iProductoRepository;

    public LocalesService(ILocalesRepository iLocalesRepository, IProductoRepository iProductoRepository) {
        this.iLocalesRepository = iLocalesRepository;
        this.iProductoRepository = iProductoRepository;
    }

    @Override
    public List<LocalesResponse> getAllLocales() {
        return mapperToLocales(iLocalesRepository.findAll());
    }

    @Override
    public MessageInfo createLocales(LocalesRequest localesRequest) {
        Locales local = mapperToLocal(localesRequest);
        iLocalesRepository.save(local);
        return MessageInfo.accionCompleta("Se ha agregado con éxito");

    }

    @Override
    public MessageInfo deleteLocales(Long id) {
        Locales local = findByIdLocales(id);
        local.setActivo(false);
        iLocalesRepository.save(local);
        return MessageInfo.accionCompleta("Se ha eliminado con éxito");

    }

    @Override
    public MessageInfo updateLocales(LocalesRequest localesRequest) {
        Locales local = findByIdLocales(localesRequest.id());
        local.setNombreLocal(localesRequest.nombreLocal());
        local.setDireccion(local.getDireccion());
        local.setTelefono(local.getTelefono());
        iLocalesRepository.save(local);
        return MessageInfo.accionCompleta("Se ha actualizado con éxito");
    }

    @Override
    public MessageInfo createLocalesAndProduct(LocalesResponse localesResponse) {
        Locales locales = findByIdLocales(localesResponse.getId());

        localesResponse.getProductoResponses().stream()
               .map( item -> {
                   Producto producto =  findByIdProducto(item.getId());
                   producto.setLocales(locales);
                   locales.getProductos().add(producto);
                   return producto;
               } ).toList();
       iLocalesRepository.save(locales);
       return MessageInfo.accionCompleta("Se ha agregado con éxito");
    }

    @Override
    public List<SelectItem> selectLocal() {
        return iLocalesRepository.findAll().stream()
                .filter(item -> item.getActivo())
                .map(item -> SelectItem.builder()
                        .value(item.getId())
                        .label(item.getNombreLocal())
                        .build()).toList();
    }

    @Override
    public LocalesResponse findLocal(Long id) {
        Locales local = findByIdLocales(id);
        return  LocalesResponse.builder()
                .id(local.getId())
                .nombreLocal(local.getNombreLocal())
                .direccion(local.getDireccion())
                .telefono(local.getTelefono())
                .build();
    };

    private List<LocalesResponse> mapperToLocales(List<Locales>  locales){
        return locales.stream().filter(item -> item.getActivo()).map(
                item -> LocalesResponse.builder()
                        .id(item.getId())
                        .nombreLocal(item.getNombreLocal())
                        .telefono(item.getTelefono())
                        .direccion(item.getDireccion())
                        .productoResponses(item.getProductos().stream().map(item2 ->
                                ProductoResponse.builder()
                                        .id(item2.getId())
                                        .nombreProducto(item2.getNombreProducto())
                                        .stock(item2.getStock())
                                        .fechaCreacion(item2.getFechaCreacion())
                                        .build()).toList())
                        .build()
        ).toList();
    }

    private Locales findByIdLocales(Long id ){
      return iLocalesRepository.findById(id)
              .orElseThrow(() -> new ResourceNotFoundException("No se encontro el local" , 404));
    };

    private Locales mapperToLocal(LocalesRequest localesRequest){
        return Locales.builder()
                .nombreLocal(localesRequest.nombreLocal())
                .direccion(localesRequest.direccion())
                .telefono(localesRequest.telefono())
                .build();
    }

    private Producto findByIdProducto(Long id){
        return iProductoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(" No se encontro el producto" , 404 ));
    }


}
