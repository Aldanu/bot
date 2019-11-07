package com.carpooling.bot.bl;

import com.carpooling.bot.CarpoolingBot;
import com.carpooling.bot.dao.CpUserRepository;
import com.carpooling.bot.domain.CpPerson;
import com.carpooling.bot.domain.CpUser;
import com.carpooling.bot.dto.PersonDto;
import com.carpooling.bot.dto.Status;
import com.carpooling.bot.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserBl {

    CpUserRepository cpUserRepository;

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

    public List<UserDto> findAllPeople() {
        List<UserDto> userDtoList = new ArrayList<>();
        for (CpUser cpUser:cpUserRepository.findAllBySctatus(Status.ACTIVE.getStatus())) {
            userDtoList.add(new UserDto(cpUser));
        }
        return userDtoList;
    }


    public Long userRegister(String message_text, long chat_id, int step, PersonDto personDto, CarpoolingBot carpoolingBot) {
        String text;
        switch (step) {
            case -1:
                carpoolingBot.sendMessage(chat_id, "Para usar la aplicacion primero se registrará:");
                text= "Ingrese sus apellidos";
                step = 1;
                carpoolingBot.sendMessage(chat_id, text); // Sending our message object to user
                break;
            case 1:
                CarpoolingBot.personDto.setLast_name(message_text);
                text= "Ingreses su nombres";
                step = 2;
                carpoolingBot.sendMessage(chat_id, text); // Sending our message object to user
                break;
            case 2:
                CarpoolingBot.personDto.setFirst_name(message_text);
                text="Ingrese su número de celular";
                step = 3;
                carpoolingBot.sendMessage(chat_id, text);

                break;
            case 3:
                CarpoolingBot.personDto.setCellphone_num(message_text);
                text="Ingrese su número de carnet";
                step = 4;
                carpoolingBot.sendMessage(chat_id, text);
                break;
            case 4:
                CarpoolingBot.personDto.setCi_number(message_text);
                String val = "Nombres = " + personDto.getFirst_name() + "\nApellidos = " + personDto.getLast_name() + "\nCelular = " + personDto.getCellphone_num() + "\nC.I. = " + personDto.getCi_number();
                step = 0;
                text="Registro exitoso con los siguientes datos: \n" + val;
                carpoolingBot.sendMessage(chat_id, text);
                break;
        }
    return Long.valueOf(step);
    }





}
