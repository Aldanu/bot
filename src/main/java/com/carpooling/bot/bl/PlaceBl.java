package com.carpooling.bot.bl;

import com.carpooling.bot.dao.CpPlaceRepository;
import com.carpooling.bot.dao.CpZoneRepository;
import com.carpooling.bot.domain.CpPlace;
import com.carpooling.bot.domain.CpZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaceBl {
    CpPlaceRepository cpPlaceRepository;

    public PlaceBl(){
        cpPlaceRepository = null;
    }
    @Autowired
    public PlaceBl(CpPlaceRepository cpPlaceRepository) {
        this.cpPlaceRepository = cpPlaceRepository;
    }

    public List<CpPlace> all(){
        List<CpPlace> result = new ArrayList<>();
        List<CpPlace> places = cpPlaceRepository.findAll();
        for(CpPlace place: places){
            if(place.getStatus() == 1){
                result.add(place);
            }
        }
        return result;
    }
    public List<CpPlace> findByZone(CpZone zone){
        List<CpPlace> result = new ArrayList<>();
        for(CpPlace place:all()){
            if(place.getZoneId().getZoneId() == zone.getZoneId()){
                result.add(place);
            }
        }
        return result;
    }
}
