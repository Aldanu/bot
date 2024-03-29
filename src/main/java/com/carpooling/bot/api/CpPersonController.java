package com.carpooling.bot.api;

import com.carpooling.bot.dao.CpPersonRepository;
import com.carpooling.bot.domain.CpPerson;
import com.carpooling.bot.dto.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/person")
public class CpPersonController {
    private CpPersonRepository cpPersonRepository;

    @Autowired
    public CpPersonController(CpPersonRepository cpPersonRepository){
        this.cpPersonRepository = cpPersonRepository;
    }

    @RequestMapping(value = "/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<PersonDto> all(){
        List<PersonDto> personDtoList = new ArrayList<>();
        for(CpPerson cpPerson: cpPersonRepository.findAll()){
            personDtoList.add(new PersonDto(cpPerson));
        }
        return personDtoList;
    }
}
