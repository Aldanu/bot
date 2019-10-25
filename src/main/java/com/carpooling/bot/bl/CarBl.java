package com.carpooling.bot.bl;

import com.carpooling.bot.CarpoolingBot;
import com.carpooling.bot.dto.CarDto;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class CarBl {

    private void registroVehiculo(Update update, int paso, CarDto car) {
        String message_text = update.getMessage().getText();
        switch (paso) {
            case 0:

                car.setMarca(message_text);
                long chat_id = update.getMessage().getChatId();
                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("Cual es el modelo?");
                /*try {
                    CarpoolingBot.sendMessage(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }*/
                paso = 1;
                break;
            case 1:
                car.setModelo(message_text);
                long chat_id1 = update.getMessage().getChatId();
                SendMessage message1 = new SendMessage() // Create a message object object
                        .setChatId(chat_id1)
                        .setText("Cual es la placa?");
                /*try {
                    //execute(message1); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }*/
                paso = 2;
                break;
            case 2:
                /*placa = message_text;
                long chat_id2 = update.getMessage().getChatId();
                SendMessage message2 = new SendMessage() // Create a message object object
                        .setChatId(chat_id2)
                        .setText("Cuantos pasajeros puede llevar?");
                try {
                    execute(message2); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }*/
                paso = 3;
                break;
            case 3:
                /*asientos = message_text;
                CarDto car = new CarDto(0, marca, modelo, placa, Integer.parseInt(asientos));
                String val = "Marca = " + car.getMarca() + "\nModelo = " + car.getModelo() + "\nPlaca = " + car.getPlaca() + "\nAsientos = " + car.getAsientos();
                long chat_id3 = update.getMessage().getChatId();
                SendMessage message3 = new SendMessage() // Create a message object object
                        .setChatId(chat_id3)
                        .setText("Registro exitoso con los siguientes datos: \n" + val);
                try {
                    execute(message3); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                conv = 0;*/
                paso = 0;
                break;
        }
    }
}
