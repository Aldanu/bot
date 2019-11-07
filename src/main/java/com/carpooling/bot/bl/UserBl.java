package com.carpooling.bot.bl;

import com.carpooling.bot.CarpoolingBot;

public class UserBl {
    public int userRegister(String message_text, long chat_id, int step, CarpoolingBot carpoolingBot) {
    String text;
    switch (step) {
        case 0:
            text= "Ingrese sus apellidos";
            step = 1;
            carpoolingBot.sendMessage(chat_id, text); // Sending our message object to user
            break;
        case 1:
            CarpoolingBot.cardto.setMarca(message_text);
            text= "Ingrese su nombre";
            step = 2;
            carpoolingBot.sendMessage(chat_id, text); // Sending our message object to user
            break;
        case 2:
            CarpoolingBot.cardto.setModelo(message_text);
            text="Ingrese su número de celular";
            step = 3;
            carpoolingBot.sendMessage(chat_id, text);

            break;
        case 3:
            CarpoolingBot.cardto.setAsientos(Integer.parseInt(message_text));
            String val = "Marca = ";
            step = 0;
            text="Registro exitoso con los siguientes datos: \n" + val;
            carpoolingBot.sendMessage(chat_id, text);
            break;
    }
    return step;
}



}
