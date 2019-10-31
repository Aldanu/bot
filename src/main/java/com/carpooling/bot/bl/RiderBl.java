package com.carpooling.bot.bl;

import com.carpooling.bot.dao.CpRiderRepository;
import com.carpooling.bot.domain.CpRiderEntity;

import java.util.Optional;

public class RiderBl {



    CpRiderRepository cpRiderRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public RiderBl(CpRiderRepository cpRiderRepository) {
        this.cpRiderRepository = cpRiderRepository;
    }

    public CpRiderEntity findRiderById(Integer pk){
        Optional<CpRiderEntity> optional = this.cpRiderRepository.findById(pk);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Record cannot found for CpPerson with ID: " + pk);
        }
    }


}
