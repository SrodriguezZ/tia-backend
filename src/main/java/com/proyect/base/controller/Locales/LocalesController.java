package com.proyect.base.controller.Locales;

import com.proyect.base.persistencia.dto.Locales.LocalesRequest;
import com.proyect.base.persistencia.dto.Locales.LocalesResponse;
import com.proyect.base.persistencia.dto.select.SelectItem;
import com.proyect.base.presentation.interfaces.Locales.ILocalesService;
import com.proyect.base.util.MessageInfo;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/locales")
public class LocalesController {

    private final ILocalesService iLocalesService;

    public LocalesController(ILocalesService iLocalesService) {
        this.iLocalesService = iLocalesService;
    }

    @GetMapping
    public ResponseEntity<List<LocalesResponse>> getLocales( ){
        return  new ResponseEntity<>(iLocalesService.getAllLocales() , HttpStatus.OK);
    }

    @GetMapping("/select")
    public ResponseEntity<List<SelectItem>> getSelect( ){
        return  new ResponseEntity<>(iLocalesService.selectLocal() , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalesResponse> getSelect( @PathVariable("id") Long id){
        return  new ResponseEntity<>(iLocalesService.findLocal(id) , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MessageInfo> postLocales(@Valid @RequestBody LocalesRequest localesRequest){
        return  new ResponseEntity<>(iLocalesService.createLocales(localesRequest) ,HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<MessageInfo> postLocalesProducto(@RequestBody LocalesResponse localesResponse){
        log.info("Recibido localesResponse: {}", localesResponse);
        return  new ResponseEntity<>(iLocalesService.createLocalesAndProduct(localesResponse) ,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<MessageInfo> putLocales(@Valid @RequestBody LocalesRequest localesRequest){
        return  new ResponseEntity<>(iLocalesService.updateLocales(localesRequest) ,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageInfo> deleteLocales(@PathVariable("id")Long id){
        return  new ResponseEntity<>(iLocalesService.deleteLocales(id) ,HttpStatus.OK);
    }

}
