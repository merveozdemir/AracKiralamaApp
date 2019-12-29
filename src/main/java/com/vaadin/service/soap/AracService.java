package com.vaadin.service.soap;

import com.vaadin.domain.Arac;
import com.vaadin.service.dto.AracDto;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface AracService {

    @WebMethod
    AracDto saveArac(AracDto aracDto);

    @WebMethod
    List<AracDto> findAllArac();

}
