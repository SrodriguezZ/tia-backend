package com.proyect.base.presentation.interfaces.Locales;

import com.proyect.base.persistencia.dto.Locales.LocalesRequest;
import com.proyect.base.persistencia.dto.Locales.LocalesResponse;
import com.proyect.base.persistencia.dto.select.SelectItem;
import com.proyect.base.util.MessageInfo;

import java.util.List;

public interface ILocalesService {
    List<LocalesResponse> getAllLocales();
    MessageInfo createLocales(LocalesRequest localesRequest);
    MessageInfo deleteLocales(Long id);
    MessageInfo updateLocales(LocalesRequest localesRequest);

    MessageInfo createLocalesAndProduct(LocalesResponse localesResponse);
    List<SelectItem> selectLocal ();
    LocalesResponse  findLocal(Long id);
}
