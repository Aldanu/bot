package com.carpooling.bot.bl;

import com.carpooling.bot.dao.CpPersonRepository;
import com.carpooling.bot.domain.CpRiderEntity;
import com.carpooling.bot.dto.RiderDto;
import com.carpooling.bot.dto.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RiderBl {



    CpPersonRepository cpPersonRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public RiderBl(CpPersonRepository cpPersonRepository) {
        this.cpPersonRepository = cpPersonRepository;
    }

    public CpRiderEntity findRiderById(Integer pk){
        Optional<CpRiderEntity> optional = this.cpPersonRepository.findById(pk);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Record cannot found for CpPerson with ID: " + pk);
        }
    }


    public List<RiderDto> findAllPeople() {
        List<RiderDto> riderDtoList = new ArrayList<>();
        for (CpRiderEntity cpRiderEntity: cpPersonRepository.findAllByStatus(Status.ACTIVE.getStatus())) {
            riderDtoList.add(new RiderDto(cpRiderEntity));
        }
        return riderDtoList;
    }


}
