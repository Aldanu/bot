package com.carpooling.bot;

import com.carpooling.bot.dto.CarDto;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class CarpoolingBot extends TelegramLongPollingBot {

    private long idChat=0;
    private int conv=0;
    private int paso=0;
    private String marca="", modelo="", placa="", asientos="";
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            if (message_text.equals("/registrar_vehiculo") || conv==1) {
                if(idChat!=chat_id){
                    SendMessage message = new SendMessage() // Create a message object object
                            .setChatId(chat_id)
                            .setText("Cual es la marca?");
                    try {
                        execute(message); // Sending our message object to user
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    idChat=chat_id;
                    conv=1;
                }else{
                    registroVehiculo(update);
                }
            }else{
                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText(message_text);
                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "TheCarpoolerBot";
    }

    @Override
    public String getBotToken() {
        return "949452837:AAHuj-LyPG9VHc1vxIoGRJ7iMIyOoemEXHk";
    }

    public void sendMessage(Long chat_id,String message_text){
        /*try {
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }*/
    }



    private void registroVehiculo(Update update){
        String message_text = update.getMessage().getText();
        switch (paso){
            case 0:

                marca=message_text;
                long chat_id = update.getMessage().getChatId();
                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("Cual es el modelo?");
                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                paso=1;
                break;
            case 1:
                modelo=message_text;
                long chat_id1 = update.getMessage().getChatId();
                SendMessage message1 = new SendMessage() // Create a message object object
                        .setChatId(chat_id1)
                        .setText("Cual es la placa?");
                try {
                    execute(message1); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                paso=2;
                break;
            case 2:
                placa=message_text;
                long chat_id2 = update.getMessage().getChatId();
                SendMessage message2 = new SendMessage() // Create a message object object
                        .setChatId(chat_id2)
                        .setText("Cuantos pasajeros puede llevar?");
                try {
                    execute(message2); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                paso=3;
                break;
            case 3:
                asientos=message_text;
                CarDto car=new CarDto(0, marca, modelo, placa, Integer.parseInt(asientos));
                String val="Marca = "+car.getMarca()+"\nModelo = "+car.getModelo()+"\nPlaca = "+car.getPlaca()+"\nAsientos = "+car.getAsientos();
                long chat_id3 = update.getMessage().getChatId();
                SendMessage message3 = new SendMessage() // Create a message object object
                        .setChatId(chat_id3)
                        .setText("Registro exitoso con los siguientes datos: \n"+val);
                try {
                    execute(message3); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                conv=0;
                paso=0;
                break;
        }
    }



}