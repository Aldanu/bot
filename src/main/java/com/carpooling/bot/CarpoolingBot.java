package com.carpooling.bot;

import com.carpooling.bot.bl.CarBl;
import com.carpooling.bot.dto.CarDto;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.toIntExact;

@Component
public class CarpoolingBot extends TelegramLongPollingBot {
    public CarBl carBl=new CarBl();
    public static CarDto cardto=new CarDto();

    private int conversation=0;
    private int step=0;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            if (message_text.equals("/registrar_vehiculo") || conversation==1) {
                conversation=1;
                    step=carBl.carRegister(update.getMessage().getText(), update.getMessage().getChatId(), step, cardto, new CarpoolingBot());
                if (step==0) conversation=0;

                   // carRegister(update.getMessage().getText(), update.getMessage().getChatId());

            }
            if(message_text.equals("/iniciar")){
                SendMessage message =custom_keyboard(chat_id, new SendMessage());
                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }
        }
        if (update.hasCallbackQuery()) {
            // Set variables
            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();

            if (call_data.equals("1")) {
                sendMessage(chat_id, "Eligio carpooler");
            }
            if (call_data.equals("2")) {
                String answer = "Updated222222222222t";
                EditMessageText new_message = new EditMessageText()
                        .setChatId(chat_id)
                        .setMessageId(toIntExact(message_id))
                        .setText(answer);
                try {
                    execute(new_message);
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

    private SendMessage custom_keyboard(long chat_id, SendMessage message){
        message
                .setChatId(chat_id)
                .setText("Como cual desea entrar?");
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("Carpooler").setCallbackData("1"));
        rowInline.add(new InlineKeyboardButton().setText("Rider").setCallbackData("2"));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);
        return message;
    }

/*
    private void registroCarpooler(Update update){
        String message_text = update.getMessage().getText();
        long chat_id = update.getMessage().getChatId();
        SendMessage message= new SendMessage();
        switch (step){
            case 0:
                message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("Cual es la marca?");
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
*/
    public void menu(String message_text, long chat_id){
        SendMessage message = new SendMessage();
        String text="";
        switch(step){
            case 0:
                text="Ingresará conmo rider o carpooler?";

                break;
            case 100:
                text="A donde desea ir?";

                break;
            case 101:
                text="Desde donde desea partir?";
                break;
            case  102:
                break;
        }
        sendMessage(chat_id, text);
    }
/*
    private void carRegister(String message_text, long chat_id){
        SendMessage message= new SendMessage();
        String text="";
        switch (step){
            case 0:
                text="Cual es la marca?";
                step=1;
                break;
            case 1:
                marca=message_text;
                text="Cual es el modelo?";
                step=2;
                break;
            case 2:
                modelo=message_text;
                text="Cual es la placa?";
                step=3;
                break;
            case 3:
                placa=message_text;
                text="Cuantos pasajeros puede llevar?";
                step=4;
                break;
            case 4:
                asientos=message_text;
                CarDto car=new CarDto(0, marca, modelo, placa, Integer.parseInt(asientos));
                String val="Marca = "+car.getMarca()+"\nModelo = "+car.getModelo()+"\nPlaca = "+car.getPlaca()+"\nAsientos = "+car.getAsientos();
                text="Registro exitoso con los siguientes datos: \n"+val;
                conversation=0;
                step=0;
                break;
        }
        sendMessage(chat_id, text);
    }*/
}