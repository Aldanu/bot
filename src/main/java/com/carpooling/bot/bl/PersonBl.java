package com.carpooling.bot.bl;

import com.carpooling.bot.dao.CpPersonRepository;
import com.carpooling.bot.domain.CpPerson;
import com.carpooling.bot.dto.PersonDto;
import com.carpooling.bot.dto.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonBl {



    CpPersonRepository cpPersonRepository;

    @Autowired
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

    public CpPerson findPersonById(Integer id){
        return cpPersonRepository.findById(id).get();
    }

}