package com.carpooling.bot.bl;

import com.carpooling.bot.dao.CpTravelPlaceRepository;
import com.carpooling.bot.dao.CpTravelRepository;
import com.carpooling.bot.domain.CpTravel;
import com.carpooling.bot.domain.CpTravelPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TravelPlaceBl {
    private CpTravelPlaceRepository cpTravelPlaceRepository;
    private CpTravelRepository cpTravelRepository;

    public TravelPlaceBl(){
        this.cpTravelPlaceRepository = null;
        this.cpTravelRepository = null;
    }

    @Autowired
    public TravelPlaceBl(CpTravelPlaceRepository cpTravelPlaceRepository, CpTravelRepository cpTravelRepository) {
        this.cpTravelPlaceRepository = cpTravelPlaceRepository;
        this.cpTravelRepository = cpTravelRepository;
    }

    public int getLastPosition(CpTravel travel){
        int result = 0;
        for(CpTravelPlace travelPlace:cpTravelPlaceRepository.findAll()){
            if(travelPlace.getTravelId().getTravelId() == travel.getTravelId()){
                result = travelPlace.getposition();
            }
        }
        return result+1;
    }
    public String routeString(CpTravel travel){
        String result = "";
        for(CpTravelPlace travelPlace:cpTravelPlaceRepository.findAll()){
            if(travelPlace.getTravelId().getTravelId() == travel.getTravelId()){
                if(travelPlace.getposition()==1){
                    result += ("Partida: "+travelPlace.getPlaceId().getName()) + "\n";
                }
                else{
                    result += ("Parada: "+travelPlace.getPlaceId().getName() + "\n");
                }
            }
        }
        result+="Fin";
        return  result;
    }
}
