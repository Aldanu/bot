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
        cpZoneRepository = null;
    }
    @Autowired
    public ZoneBl(CpZoneRepository cpZoneRepository) {
        this.cpZoneRepository = cpZoneRepository;
    }
    public List<CpZone> all(){
        List<CpZone> zoneList = new ArrayList<CpZone>();
        List<CpZone> all = cpZoneRepository.findAll();
        for(CpZone zone: all){
            if(zone.getStatus() == 1){
                LOGGER.info("Zona: "+zone.getName()+" Fue agregada a la lista de botones");
                zoneList.add(zone);
            }
        }
        return zoneList;
    }
    public CpZone findByName(String name){
        CpZone result = new CpZone();
        List<CpZone> zones = all();
        for(CpZone zone: zones){
            if(zone.getName().equals(name)){
                result = zone;
            }
        }
        return result;
    }
}
