package com.carpooling.bot.api;

import com.carpooling.bot.dao.CpTravelSearchRepository;
import com.carpooling.bot.domain.CpTravelSearch;
import com.carpooling.bot.dto.CarDto;
import com.carpooling.bot.dto.TravelSearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/travelsearch")
public class CpTravelSearchController {
    private CpTravelSearchRepository cpTravelSearchRepository;

    @Autowired
    public CpTravelSearchController(CpTravelSearchRepository cpTravelSearchRepository){
        this.cpTravelSearchRepository = cpTravelSearchRepository;
    }

    @RequestMapping(value = "/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<TravelSearchDto> all(){
        List<TravelSearchDto> TravelSearchDtoList = new ArrayList<>();
        for(CpTravelSearch cpTravelSearch: cpTravelSearchRepository.findAll()){
            TravelSearchDtoList.add(new TravelSearchDto(cpTravelSearch));
        }
        return TravelSearchDtoList;
    }
}
