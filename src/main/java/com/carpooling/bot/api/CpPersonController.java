package com.carpooling.bot.api;

import com.carpooling.bot.dao.CpPersonRepository;
import com.carpooling.bot.domain.CpPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CpPersonController {
    private CpPersonRepository cpPersonRepository;

    @Autowired
    public CpPersonController(CpPersonRepository cpPersonRepository){
    }

    @GetMapping("/person")
    List<CpPerson> all(){
        return cpPersonRepository.findAll();
    }
}
