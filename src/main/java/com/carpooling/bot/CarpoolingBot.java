package com.carpooling.bot;

import com.carpooling.bot.bl.CarBl;
import com.carpooling.bot.dto.CarDto;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class CarpoolingBot extends TelegramLongPollingBot {
    public CarBl carBl;

    private long idChat=0;
    private int conversation=0;
    private int step=0;
    private String marca="", modelo="", placa="", asientos="";
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            if (message_text.equals("/registrar_vehiculo") || conversation==1) {
                CarDto cardto=new CarDto(0, "", "", "", 0);
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
                    conversation=1;
                }else{
                    /*paso=carBl.registroVehiculo(update, paso, cardto);
                    if (paso==0) conv=0;*/
                    registroVehiculo(update.getMessage().getText(), update.getMessage().getChatId());
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

    public void sendMessage(long chat_id, String text){
        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chat_id)
                .setText(text);
        try {
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    private void registroVehiculo(Update update){
        String message_text = update.getMessage().getText();
        long chat_id = update.getMessage().getChatId();
        SendMessage message= new SendMessage();
        switch (step){
            case 0:
                message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("Cual es el modelo?");
                step=1;
                break;
            case 1:
                marca=message_text;
                message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("Cual es el modelo?");
                step=2;
                break;
            case 2:
                modelo=message_text;
                message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("Cual es la placa?");
                step=3;
                break;
            case 3:
                placa=message_text;
                message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("Cuantos pasajeros puede llevar?");
                step=4;
                break;
            case 4:
                asientos=message_text;
                CarDto car=new CarDto(0, marca, modelo, placa, Integer.parseInt(asientos));
                String val="Marca = "+car.getMarca()+"\nModelo = "+car.getModelo()+"\nPlaca = "+car.getPlaca()+"\nAsientos = "+car.getAsientos();
                message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("Registro exitoso con los siguientes datos: \n"+val);
                conversation=0;
                step=0;
                break;
        }
        try {
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void registroVehiculo(String message_text, long chat_id){
        SendMessage message= new SendMessage();
        switch (step){
            case 0:
                message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("Cual es el modelo?");
                step=1;
                break;
            case 1:
                marca=message_text;
                message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("Cual es el modelo?");
                step=2;
                break;
            case 2:
                modelo=message_text;
                message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("Cual es la placa?");
                step=3;
                break;
            case 3:
                placa=message_text;
                message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("Cuantos pasajeros puede llevar?");
                step=4;
                break;
            case 4:
                asientos=message_text;
                CarDto car=new CarDto(0, marca, modelo, placa, Integer.parseInt(asientos));
                String val="Marca = "+car.getMarca()+"\nModelo = "+car.getModelo()+"\nPlaca = "+car.getPlaca()+"\nAsientos = "+car.getAsientos();
                message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("Registro exitoso con los siguientes datos: \n"+val);
                conversation=0;
                step=0;
                break;
        }
        try {
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}