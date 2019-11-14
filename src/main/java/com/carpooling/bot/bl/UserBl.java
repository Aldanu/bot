package com.carpooling.bot.bl;

import com.carpooling.bot.dao.CpUserRepository;
import com.carpooling.bot.domain.CpPerson;
import com.carpooling.bot.domain.CpUser;
import com.carpooling.bot.dto.PersonDto;
import com.carpooling.bot.dto.Status;
import com.carpooling.bot.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserBl {

    CpUserRepository cpUserRepository;

    public UserBl(){
        cpUserRepository = null;
    }

    @Autowired
    public UserBl(CpUserRepository cpUserRepository) {
        this.cpUserRepository = cpUserRepository;
    }

    public CpUser findPersonById(Integer pk) {
        Optional<CpUser> optional = this.cpUserRepository.findById(pk);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Record cannot found for CpPerson with ID: " + pk);
        }
    }

    public CpUser findUserByTelegramUserId(Update update){
        CpUser cpUser = cpUserRepository.findByBotUserId(update.getMessage().getFrom().getId().toString());
        return cpUser;
    }
    /*public List<UserDto> findAllPeople() {
        List<UserDto> userDtoList = new ArrayList<>();
        for (CpUser cpUser:cpUserRepository.findAllByStatus(Status.ACTIVE.getStatus())) {
            userDtoList.add(new UserDto(cpUser));
        }
        return userDtoList;
    }*/






}
