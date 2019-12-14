package com.carpooling.bot.bl;

import com.carpooling.bot.dao.CpCarRepository;
import com.carpooling.bot.dao.CpTravelRepository;
import com.carpooling.bot.domain.CpCar;
import com.carpooling.bot.domain.CpPerson;
import com.carpooling.bot.domain.CpTravel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Service
@Transactional
public class TravelBl {
    CpTravelRepository cpTravelRepository;
    CpCarRepository cpCarRepository;
    public TravelBl(){
        cpTravelRepository = null;
    }
    @Autowired
    public TravelBl(CpTravelRepository cpTravelRepository, CpCarRepository cpCarRepository) {
        this.cpTravelRepository = cpTravelRepository;
        this.cpCarRepository = cpCarRepository;
    }
    public List<CpTravel> all(){
        List<CpTravel> travelList = new ArrayList<>();
        List<CpTravel> all = cpTravelRepository.findAll();
        for(CpTravel travel: all){
            if(travel.getStatus() == 1){
                travelList.add(travel);
            }
        }
        return travelList;
    }
    public CpTravel getLastTravel(List<CpTravel> travels,CpPerson cpPerson){
        CpTravel result = new CpTravel();
        for(CpTravel travel: travels){
            LOGGER.info("TEST");
            LOGGER.info(travel.getCarId().toStringOption());
            if(travel.getCarId().getPersonId().getPersonId() == cpPerson.getPersonId()){
                result = travel;
            }
        }
        return  result;
    }

    public List<CpTravel> getTravelByZone(String zone){
        List<CpTravel> travelList = new ArrayList<>();
        /*List<CpTravel> all = cpTravelRepository.findByZone(zone);
        for(CpTravel travel: all){

            if(travel.getStatus() == 0){

                travelList.add(travel);
            }
        }*/
        return  travelList;
    }
}
