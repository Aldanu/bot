package com.carpooling.bot.bot;

import com.carpooling.bot.CarpoolingBot;
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


public class MainBot extends TelegramLongPollingBot {
    //variables used to send data to other classes
    private CarBl carBl;
    private UserBl userBl;
    //This both classes should be changed to an array in order to allow multiuser saving data
    public static CarDto cardto=new CarDto();
    public static PersonDto personDto=new PersonDto();
    //The array gets the data from the user to manage their conversation
    //The LONG has 4 values, chat id, type of user, conversation, and step of the conversation
    private ArrayList<Long[]> data=new ArrayList<>();

    private final static Logger LOGGER = Logger.getLogger(CarpoolingBot.class.getName());

    public MainBot(CarBl carBl,UserBl userBl){
        this.carBl = carBl;
        this.userBl = userBl;
    }

    @Override
    public void onUpdateReceived(Update update) {
        //Here it verifies if the user has used the bot, if not newChat is true and the add the id of the user
        if(newChat(update.getMessage().getChatId())){
            addId(update.getMessage().getChatId());
            LOGGER.info("Id added: "+update.getMessage().getChatId());
        }
        //Then it send the message to the function ReplyMessage
        LOGGER.info("Id already exists"+update.getMessage().getChatId());
        if (update.hasMessage() && update.getMessage().hasText()) {
            ReplyMessage(update.getMessage());
        }
    }

    //This function differentiates the message received
    private void ReplyMessage(Message message) {
        String message_text = message.getText();
        long chat_id = message.getChatId();
        //here the position where the chatId is stored is obtained
        int position=getId(chat_id);
        //If the user writes /iniciar or it is in process of registering
        //the application whether will register the user data if its the first time he presses /iniciar
        //or the application will ask the user what kind of user it will be by calling createUserType
        if ((message_text.equals("/iniciar") || data.get(position)[3].equals(2L)) ) {
            if(!register(chat_id, position, message_text) && data.get(position)[1].equals(0L)){
                LOGGER.info("Id registering");
            }else{

                createUserType(chat_id);
            }
        }
        //If the user wants to register its car he has to write /registrar_vehiculo and has to be a carpooler user type
        //if the user is in progress of registering it will continue
        if (((message_text.equals("/registrar_vehiculo") || data.get(position)[3].equals(1L))) && data.get(position)[1].equals(2L)) {
            data.get(position)[3]= Long.valueOf(1);
            data.get(position)[2]= Long.valueOf(carBl.carRegister(message_text, chat_id, Math.toIntExact(data.get(position)[2]), cardto, new CarpoolingBot()));

            if (data.get(position)[2].equals(0L)) data.get(position)[3]= Long.valueOf(0);
        }
        //This is the responses when the user press carpooler or rider at the start
        //It set the [1] value of the array of that user
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
    //return the position of the Id searched
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
    //Here it send a message to the user and removes  any custom keyboard
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

    //Here the user decides whether it will be a carpooler or a rider and creates a custom keyboard for it
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
    //This function is in process of being created, this will be the main function where it will decides in what conversation is a user
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
    //This adds a user into the arrayList
    private void addId(Long chatId) {
        Long[] dataCreation = new Long[4];
        //ChatId
        dataCreation[0]=chatId;
        //1 for rider 2 for carpooler in order to limit the user choices
        dataCreation[1]=0L;
        //Step of the conversation which the user is in, is set to -1 because is not in any conversation and not register,
        // then it by default should be on 0.
        dataCreation[2]=-1L;
        //Conversation in which the user is in
        dataCreation[3]=0L;
        data.add(dataCreation);
    }

    //This search if the ChatId exist in the arrayList
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

    //Here the user is being registered
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


}
