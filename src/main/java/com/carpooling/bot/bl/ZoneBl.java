package com.carpooling.bot.bl;

import com.carpooling.bot.dao.CpZoneRepository;
import com.carpooling.bot.domain.CpZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Service
public class ZoneBl {
    CpZoneRepository cpZoneRepository;

    public ZoneBl(){
        LOGGER.info("ApAsasas  1 1 1 11  11 1  1");
        cpZoneRepository = null;
        LOGGER.info("ApAsasas  2 2 2 2 2 2 2 2 ");
    }
    @Autowired
    public ZoneBl(CpZoneRepository cpZoneRepository) {
        LOGGER.info("ApAsasas  3 3 3 3 3 3 33 3 3 3");
        this.cpZoneRepository = cpZoneRepository;
        LOGGER.info("ApAsasas  4 4 4 4 4 4 4 4 4 4 4 4");
    }
    public List<CpZone> all(){
        LOGGER.info("Antes de ZXON");
        List<CpZone> zoneList = new ArrayList<CpZone>();
        LOGGER.info("ZonasBl");
        List<CpZone> all = cpZoneRepository.findAll();
        LOGGER.info("ZonasBl obtiene las zonas de la base de datos");
        for(CpZone zone: all){
            if(zone.getStatus() == 1){
                LOGGER.info("Zona: "+zone.getName()+" Fue agregada a la lista de botones");
                zoneList.add(zone);
            }
        }
        return zoneList;
    }
}
