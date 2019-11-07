package com.carpooling.bot.api;

import com.carpooling.bot.dao.CpCarRepository;
import com.carpooling.bot.dao.CpPersonRepository;
import com.carpooling.bot.domain.CpCar;
import com.carpooling.bot.domain.CpPerson;
import com.carpooling.bot.dto.CarDto;
import com.carpooling.bot.dto.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/car")
public class CpCarController {
    private CpCarRepository cpCarRepository;

    @Autowired
    public CpCarController(CpCarRepository cpCarRepository){
        this.cpCarRepository = cpCarRepository;
    }

    @RequestMapping(value = "/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<CarDto> all(){
        List<CarDto> carDtoList = new ArrayList<>();
        for(CpCar cpCar: cpCarRepository.findAll()){
            carDtoList.add(new CarDto(cpCar));
        }
        return carDtoList;
    }
}
