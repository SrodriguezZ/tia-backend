package com.proyect.base.util;

import com.proyect.base.persistencia.entity.Locales;
import com.proyect.base.persistencia.entity.Producto;
import com.proyect.base.presentation.repository.ILocalesRepository;
import com.proyect.base.presentation.repository.IProductoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class DataLoader implements CommandLineRunner {

    private  final IProductoRepository iProductoRepository;
    private  final ILocalesRepository iLocalesRepository;

    public DataLoader(IProductoRepository iProductoRepository,ILocalesRepository iLocalesRepository) {
        this.iProductoRepository = iProductoRepository;
        this.iLocalesRepository = iLocalesRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(iProductoRepository.findAll().size() == 0){
            log.info("Cargando datos productos");
            List<Producto> productos = List.of( Producto.builder()
                    .nombreProducto("Manzana")
                    .stock(100L)
                    .build(),
                    Producto.builder()
                    .nombreProducto("Pera")
                    .stock(150L)
                    .build(),
                    Producto.builder()
                            .nombreProducto("Uva")
                            .stock(150L)
                            .build());

            iProductoRepository.saveAll(productos);
            log.info("Datos cargado productos");
        }
        if(iLocalesRepository.findAll().size()  == 0){
            log.info("Cargando locales");
            iLocalesRepository.saveAll(
                    List.of(Locales.builder()
                            .nombreLocal("Tia peca")
                            .telefono("0999999")
                            .direccion("Via Duale")
                    .build(),Locales.builder()
                            .nombreLocal("Tia Centro")
                            .telefono("0999999")
                            .direccion("9 Octubre")
                            .build()
                    ));
            log.info("Datos cargados locales");
        }
    }
}
