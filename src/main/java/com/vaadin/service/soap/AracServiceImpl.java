package com.vaadin.service.soap;

import com.vaadin.dao.AracDao;
import com.vaadin.domain.Arac;
import com.vaadin.service.converter.AracConverter;
import com.vaadin.service.dto.AracDto;

import javax.jws.WebService;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet(urlPatterns = "/services/*", name = "AracServiceServlet", loadOnStartup = 1)
@WebListener(value = "com.sun.xml.ws.transport.http.servlet.WSServletContextListener")
@WebService(endpointInterface = "com.vaadin.service.soap.AracService", name = "AracService")
public class AracServiceImpl implements AracService {
    @Override
    public AracDto saveArac(AracDto aracDto) {
        AracConverter aracConverter = new AracConverter();
        Arac arac = aracConverter.convertToArac(aracDto);

        AracDao aracDao = new AracDao();
        aracDao.araciKaydet(arac);

        aracDto = aracConverter.convertToAracDto(arac);
        return aracDto;
    }

    public List<AracDto> findAllArac() {
        return null;
    }
}
