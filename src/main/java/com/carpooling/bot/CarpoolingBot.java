package com.carpooling.bot;

import com.carpooling.bot.bl.CarBl;
import com.carpooling.bot.bl.UserBl;
import com.carpooling.bot.dto.CarDto;
import com.carpooling.bot.dto.PersonDto;
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
    private CarBl carBl=new CarBl();
    private UserBl userBl=new UserBl();
    public static CarDto cardto=new CarDto();
    private ArrayList <Long[]> data=new ArrayList<>();
    public static PersonDto personDto=new PersonDto();
    //private int conversation=0;
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

    private boolean register(Long chatId, int position, String message_text) {
        boolean registered=false;
        if (data.get(position)[2].equals(0L)){
            registered=true;
            data.get(position)[2]= Long.valueOf(0L);
            data.get(position)[3]= Long.valueOf(0);
        }else{
            data.get(position)[3] = Long.valueOf(2);
            data.get(position)[2] = userBl.userRegister(message_text, chatId, Math.toIntExact(data.get(position)[2]), personDto, new CarpoolingBot());
        }
        return registered;
    }


    private void ReplyMessage(Message message) {
        String message_text = message.getText();
        long chat_id = message.getChatId();
        int position=getId(chat_id);

        if ((message_text.equals("/iniciar") || data.get(position)[3].equals(2L)) ) {
            LOGGER.info(data.get(position)[0]+" dsdfgsdfagdsgasdgsgsd "+data.get(position)[1].equals(0L));
            if(!register(chat_id, position, message_text) && data.get(position)[1].equals(0L)){
                LOGGER.info("Id registering");
            }else{

                createUserType(chat_id);
            }
        }
        if (((message_text.equals("/registrar_vehiculo") || data.get(position)[3].equals(1L))) && data.get(position)[1].equals(2L)) {
            data.get(position)[3]= Long.valueOf(1);
            step=carBl.carRegister(message_text, chat_id, Math.toIntExact(data.get(position)[2]), cardto, new CarpoolingBot());
            data.get(position)[2]=Long.parseLong(step+"");
            if (step==0) data.get(position)[3]= Long.valueOf(0);
        }
        if (message_text.equals("Carpooler")){
            sendMessage(chat_id, "Usted entro como:"+message_text);
            data.get(position)[1]= Long.valueOf(2);
            LOGGER.info(data.get(position)[0]+" is in Carpooler mode");
        }
        if (message_text.equals("Rider")){
            data.get(position)[1]= Long.valueOf(1);
            sendMessage(chat_id, "Usted entro como:"+message_text);
            LOGGER.info(data.get(position)[0]+" is in Rider mode");
        }
    }

    private int getId(long chat_id) {
        int position=0;
        for(int id=0; id<data.size(); id++){
            if(data.get(id)[0]==chat_id){
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
/*
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
*/
    private void addId(Long chatId) {
        Long[] dataCreation = new Long[4];
        //ChatId
        dataCreation[0]=chatId;
        //1 for rider 2 for carpooler in order to limit the user choices
        dataCreation[1]=0L;
        //Step of the conversation which the user is in
        dataCreation[2]=-1L;
        //Conversation in which the user is in
        dataCreation[3]=0L;
        data.add(dataCreation);
    }

    private boolean newChat(Long chatId) {
        boolean newChat=true;
        for (Long[] datum : data) {
            if (datum[0].equals(chatId)) {
                LOGGER.info("Id encontrado: " + datum[0]);
                newChat = false;
            }
        }
        return newChat;
    }
}