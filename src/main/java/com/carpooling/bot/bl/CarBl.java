package com.carpooling.bot.bl;

import com.carpooling.bot.CarpoolingBot;
import com.carpooling.bot.dto.CarDto;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class CarBl {
    public CarBl(){

    }
    CarpoolingBot carpoolingBot;
    public int registroVehiculo(Update update, int paso, CarDto car) {
        long chat_id = update.getMessage().getChatId();
        String message_text = update.getMessage().getText();
        String text;
        switch (paso) {
            case 0:
                car.setMarca(message_text);
                text= "Cual es el modelo?";
                paso = 1;
                carpoolingBot.sendMessage(chat_id, text); // Sending our message object to user
                break;
            case 1:
                car.setModelo(message_text);
                text="Cual es la placa?";
                paso = 2;
                carpoolingBot.sendMessage(chat_id, text);

                break;
            case 2:
                car.setPlaca(message_text);
                text="Cuantos pasajeros puede llevar?";
                paso = 3;
                carpoolingBot.sendMessage(chat_id, text);
                break;
            case 3:
                car.setAsientos(Integer.parseInt(message_text));
                String val = "Marca = " + car.getMarca() + "\nModelo = " + car.getModelo() + "\nPlaca = " + car.getPlaca() + "\nAsientos = " + car.getAsientos();
                paso = 0;
                text="Registro exitoso con los siguientes datos: \n" /*+ val*/;
                carpoolingBot.sendMessage(chat_id, text);
                break;
        }
        return paso;
    }
}
