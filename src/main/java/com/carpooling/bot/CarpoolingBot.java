package com.carpooling.bot;

import com.carpooling.bot.bl.CarBl;
import com.carpooling.bot.dto.CarDto;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class CarpoolingBot extends TelegramLongPollingBot {
    public CarBl carBl=new CarBl();
    public static CarDto cardto=new CarDto();
    private ArrayList <Long[]> datos=new ArrayList<>();
    private int conversation=0;
    private int step=0;
    private final static Logger LOGGER = Logger.getLogger(CarpoolingBot.class.getName());
    @Override
    public void onUpdateReceived(Update update) {
        if(newChat(update.getMessage().getChatId())){
            addId(update.getMessage().getChatId());
            LOGGER.info("Id added: "+update.getMessage().getChatId());
        }
        LOGGER.info("Id already exists"+update.getMessage().getChatId());
        if (update.hasMessage() && update.getMessage().hasText()) {
            ReplyMessage(update.getMessage());
        }
    }

    private void addId(Long chatId) {
        Long[] data = new Long[3];
        data[0]=chatId;
        data[1]=0L;
        data[2]=0L;
        datos.add(data);
    }

    private boolean newChat(Long chatId) {
        boolean newChat=true;
        for(int id=0; id<datos.size(); id++){
            LOGGER.info("Id: "+datos.get(id)[0]);
            if(datos.get(id)[0].equals(chatId)){
                newChat=false;
            }
        }
        return newChat;
    }

    private void ReplyMessage(Message message) {
        String message_text = message.getText();
        long chat_id = message.getChatId();
        int position=getId(chat_id);

        if (message_text.equals("/iniciar")) {
            createUserType(chat_id);
        }
        if (message_text.equals("/registrar_vehiculo") || conversation==1) {
            conversation=1;
            step=carBl.carRegister(message_text, chat_id, Math.toIntExact(datos.get(position)[2]), cardto, new CarpoolingBot());
            datos.get(position)[2]=Long.parseLong(step+"");
            if (step==0) conversation=0;
        }
        if (message_text.equals("Carpooler")){
            sendMessage(chat_id, "Usted entro como:"+message_text);
        }
        if (message_text.equals("Rider")){
            sendMessage(chat_id, "Usted entro como:"+message_text);
        }
    }

    private int getId(long chat_id) {
        int position=0;
        for(int id=0; id<datos.size(); id++){
            if(datos.get(id)[0]==chat_id){
                position=id;
            }
        }
        return position;
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
        ReplyKeyboardRemove keyboardMarkupRemove = new ReplyKeyboardRemove();
        message.setReplyMarkup(keyboardMarkupRemove);
        try {
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void createUserType(long chat_id) {
        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chat_id)
                .setText("Como desea entrar:");
        // Create ReplyKeyboardMarkup object
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Create a keyboard row
        KeyboardRow row = new KeyboardRow();
        // Set each button, you can also use KeyboardButton objects if you need something else than text
        row.add("Carpooler");
        // Add the first row to the keyboard
        keyboard.add(row);
        // Create another keyboard row
        row = new KeyboardRow();
        // Set each button for the second line
        row.add("Rider");
        // Add the second row to the keyboard
        keyboard.add(row);
        // Set the keyboard to the markup
        keyboardMarkup.setKeyboard(keyboard);
        // Add it to the message
        message.setReplyMarkup(keyboardMarkup);

        try {
            execute(message); // Sending our message object to user

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

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

}