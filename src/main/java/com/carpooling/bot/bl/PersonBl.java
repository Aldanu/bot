package com.carpooling.bot.bl;

import com.carpooling.bot.dao.CpPersonRepository;
import com.carpooling.bot.domain.CpPerson;
import com.carpooling.bot.dto.PersonDto;
import com.carpooling.bot.dto.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonBl {



    CpPersonRepository cpPersonRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public PersonBl(CpPersonRepository cpPersonRepository) {
        this.cpPersonRepository = cpPersonRepository;
    }

    public CpPerson findRiderById(Integer pk){
        Optional<CpPerson> optional = this.cpPersonRepository.findById(pk);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Record cannot found for CpPerson with ID: " + pk);
        }
    }


    public List<PersonDto> findAllPeople() {
        List<PersonDto> riderDtoList = new ArrayList<>();
        for (CpPerson cpRiderEntity: cpPersonRepository.findAllByStatus(Status.ACTIVE.getStatus())) {
            riderDtoList.add(new PersonDto(cpRiderEntity));
        }
        return riderDtoList;
    }


}