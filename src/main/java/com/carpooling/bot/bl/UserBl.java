package com.carpooling.bot.bl;

import com.carpooling.bot.CarpoolingBot;
import com.carpooling.bot.dto.PersonDto;
import org.springframework.stereotype.Service;

@Service
public class UserBl {
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
