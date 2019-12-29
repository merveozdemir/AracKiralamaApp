package com.vaadin.service.converter;

import com.vaadin.dao.AracDao;
import com.vaadin.domain.Arac;
import com.vaadin.service.dto.AracDto;

public class AracConverter {

    public Arac convertToArac(AracDto aracDto){
        Arac arac = new Arac();
        arac.setId(aracDto.getId());
        arac.setAracMarka(aracDto.getAracMarka());
        arac.setAracModel(aracDto.getAracModel());
        arac.setKoltukSayisi(aracDto.getKoltukSayisi());
        return arac;
    }

    public AracDto convertToAracDto(Arac arac){
        AracDto aracDto = new AracDto();
        aracDto.setId(arac.getId());
        aracDto.setAracMarka(arac.getAracMarka());
        aracDto.setAracModel(arac.getAracModel());
        aracDto.setKoltukSayisi(arac.getKoltukSayisi());
        return aracDto;
    }
}
