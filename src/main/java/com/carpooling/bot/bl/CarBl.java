package com.carpooling.bot.bl;

import com.carpooling.bot.CarpoolingBot;
import com.carpooling.bot.dto.CarDto;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class CarBl {
    public int carRegister(String message_text, long chat_id, int step, CarDto car, CarpoolingBot carpoolingBot) {
        String text;
        switch (step) {
            case 0:
                carpoolingBot.sendMessage(chat_id, "Para ser Carpooler debe registrar su vehiculo");
                text= "Cual es la marca?";
                step = 1;
                carpoolingBot.sendMessage(chat_id, text); // Sending our message object to user
                break;
            case 1:
                CarpoolingBot.cardto.setMarca(message_text);
                text= "Cual es el modelo?";
                step = 2;
                carpoolingBot.sendMessage(chat_id, text); // Sending our message object to user
                break;
            case 2:
                CarpoolingBot.cardto.setModelo(message_text);
                text="Cual es la placa?";
                step = 3;
                carpoolingBot.sendMessage(chat_id, text);

                break;
            case 3:
                CarpoolingBot.cardto.setPlaca(message_text);
                text="Cuantos pasajeros puede llevar?";
                step = 4;
                carpoolingBot.sendMessage(chat_id, text);
                break;
            case 4:
                CarpoolingBot.cardto.setAsientos(Integer.parseInt(message_text));
                String val = "Marca = " + car.getMarca() + "\nModelo = " + car.getModelo() + "\nPlaca = " + car.getPlaca() + "\nAsientos = " + car.getAsientos();
                step = 0;
                text="Registro exitoso con los siguientes datos: \n" + val;
                carpoolingBot.sendMessage(chat_id, text);
                break;
        }
        return step;
    }



}
