package com.carpooling.bot.bl;

import com.carpooling.bot.dao.CpTravelRepository;
import com.carpooling.bot.domain.CpTravel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Service
public class TravelBl {
    CpTravelRepository cpTravelRepository;

    public TravelBl(){
        cpTravelRepository = null;
    }
    @Autowired
    public TravelBl(CpTravelRepository cpTravelRepository) {
        this.cpTravelRepository = cpTravelRepository;
    }
    public List<CpTravel> all(){
        List<CpTravel> travelList = new ArrayList<CpTravel>();
        List<CpTravel> all = cpTravelRepository.findAll();
        for(CpTravel travel: all){
            if(travel.getStatus() == 1){
                LOGGER.info("Zona: "+travel.getDescription()+" Fue agregada a la lista de botones");
                travelList.add(travel);
            }
        }
        return travelList;
    }
}
