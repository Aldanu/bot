package com.carpooling.bot.bl;

import com.carpooling.bot.dao.CpTravelRiderRepository;
import com.carpooling.bot.domain.CpTravelRider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TravelRiderBl {
    CpTravelRiderRepository cpTravelRiderRepository;
    Validator validator = new Validator();
    public TravelRiderBl(){
        cpTravelRiderRepository = null;
    }
    @Autowired
    public TravelRiderBl(CpTravelRiderRepository cpTravelRiderRepository) {
        this.cpTravelRiderRepository = cpTravelRiderRepository;
    }
    public List<CpTravelRider> all(){
        List<CpTravelRider> travelList = new ArrayList<>();
        List<CpTravelRider> all = cpTravelRiderRepository.findAll();
        for(CpTravelRider travel: all){
            if(travel.getStatus() == 1){
                travelList.add(travel);
            }
        }
        return travelList;
    }
    /*public List<CpTravel> findActiveTravels(CpPerson person){
        List<CpTravel> result = new ArrayList<>();
        for(CpTravel travel:all()){

            if(validator.isFuture(travel.getDepartureTime()) && person.getPersonId() == travel.getCarId().getPersonId().getPersonId()){
                result.add(travel);
            }
            else{
                travel.setStatus(0);
                cpTravelRepository.save(travel);
            }
        }
        return result;
    }*/

}
