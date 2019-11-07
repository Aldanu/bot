package com.carpooling.bot.api;

import com.carpooling.bot.dao.CpCarRepository;
import com.carpooling.bot.dao.CpUserRepository;
import com.carpooling.bot.domain.CpCar;
import com.carpooling.bot.domain.CpUser;
import com.carpooling.bot.dto.CarDto;
import com.carpooling.bot.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class CpUserController {
    private CpUserRepository cpUserRepository;

    @Autowired
    public CpUserController(CpUserRepository cpUserRepository){
        this.cpUserRepository = cpUserRepository;
    }

    @RequestMapping(value = "/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserDto> all(){
        List<UserDto> userDtoList = new ArrayList<>();
        for(CpUser cpUser: cpUserRepository.findAll()){
            userDtoList.add(new UserDto(cpUser));
        }
        return userDtoList;
    }
}
