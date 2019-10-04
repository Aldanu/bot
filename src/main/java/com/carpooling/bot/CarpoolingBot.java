package com.carpooling.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class CarpoolingBot  extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            Message message = update.getMessage();

            if(message.hasText()){

                SendMessage sendMessageRequest = new SendMessage();
                sendMessageRequest.setChatId(message.getChatId().toString());
                sendMessageRequest.setText("you said: " + message.getText());
                /*try {
                    sendMessage(sendMessageRequest);
                } catch (TelegramApiException e) {

                }*/
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
}