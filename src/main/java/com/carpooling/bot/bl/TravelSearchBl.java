package com.carpooling.bot.bl;

import com.carpooling.bot.dao.CpTravelSearchRepository;
import com.carpooling.bot.domain.CpTravelSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TravelSearchBl {
    CpTravelSearchRepository cpTravelSearchRepository;
    Validator validator = new Validator();
    public TravelSearchBl(){
        cpTravelSearchRepository = null;
    }
    @Autowired
    public TravelSearchBl(CpTravelSearchRepository cpTravelSearchRepository) {
        this.cpTravelSearchRepository = cpTravelSearchRepository;
    }
    public List<CpTravelSearch> all(){
        List<CpTravelSearch> travelList = new ArrayList<>();
        List<CpTravelSearch> all = cpTravelSearchRepository.findAll();
        for(CpTravelSearch travel: all){
            travelList.add(travel);
        }
        return travelList;
    }

}
