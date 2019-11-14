package com.carpooling.bot.bot;


import com.carpooling.bot.CarpoolingBot;
import com.carpooling.bot.bl.BotBl;
import com.carpooling.bot.bl.CarBl;
import com.carpooling.bot.bl.UserBl;
import com.carpooling.bot.dto.CarDto;
import com.carpooling.bot.dto.PersonDto;
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
    private BotBl botBl;
    private CarBl carBl;
    private UserBl userBl;
    //This both classes should be changed to an array in order to allow multiuser saving data
    public static CarDto cardto=new CarDto();
    public static PersonDto personDto=new PersonDto();
    //The array gets the data from the user to manage their conversation
    //The LONG has 4 values, chat id, type of user, conversation, and step of the conversation
    private ArrayList<Long[]> data=new ArrayList<>();

    private final static Logger LOGGER = Logger.getLogger(CarpoolingBot.class.getName());

    public MainBot(BotBl botBl, CarBl carBl, UserBl userBl){
        this.botBl = botBl;
        this.carBl = carBl;
        this.userBl = userBl;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            int conversation = botBl.processUpdate(update);
            //switch moved to the response function
            response(conversation, update);
        }
    }

    //This function differentiates the message received
    /*private void ReplyMessage(Message message) {
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

                //createUserType();
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
    }*/

    @Override
    public String getBotUsername() {
        return "TheCarpoolerBot";
    }
    @Override
    public String getBotToken() {
        return "949452837:AAHuj-LyPG9VHc1vxIoGRJ7iMIyOoemEXHk";
    }

    //Here it send a message to the user and removes  any custom keyboard
    /*public void sendMessage(long chat_id, String text){
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
    }*/

    //Here the user decides whether it will be a carpooler or a rider and creates a custom keyboard for it
    private ReplyKeyboardMarkup createReplyKeyboard() {
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
        row = new KeyboardRow();
        // Set each button for the second line
        row.add("Corregir registro");
        // Add the second row to the keyboard
        keyboard.add(row);
        // Set the keyboard to the markup
        keyboardMarkup.setKeyboard(keyboard);
        // Add it to the message
        return keyboardMarkup;
    }

    private ReplyKeyboardMarkup createReplyKeyboardCarpooler() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Create a keyboard row
        KeyboardRow row = new KeyboardRow();
        // Set each button, you can also use KeyboardButton objects if you need something else than text
        row.add("Registrar Vehículo");
        // Add the first row to the keyboard
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("Ver Vehículos");
        keyboard.add(row);
        // Create another keyboard row
        row = new KeyboardRow();
        // Set each button for the second line
        row.add("Registrar Viaje");
        // Add the second row to the keyboard
        keyboard.add(row);
        row = new KeyboardRow();
        // Set each button for the second line
        row.add("Ver Viajes");
        // Add the second row to the keyboard
        keyboard.add(row);
        row = new KeyboardRow();
        // Set each button for the second line
        row.add("Volver al Menú Principal");
        // Add the second row to the keyboard
        keyboard.add(row);
        // Set the keyboard to the markup
        keyboardMarkup.setKeyboard(keyboard);
        // Add it to the message
        return keyboardMarkup;
    }

    public void response(int conversation, Update update){
        List<String> responses = new ArrayList<>();
        ReplyKeyboardMarkup rkm=null;
        switch (conversation){
            //****************************************\\
            //Here is the initial registering\\
            //****************************************\\
            case 1:
                responses.add("Bienvenido a Carpooling Bot");
                responses.add("Para usar el ChatBot debes registrarte primero");
                responses.add("Ingresa tus apellidos");
                break;
            case 2:
                responses.add("Ingresa tu nombre");
                break;
            case 3:
                responses.add("Quieres usar la aplicacion como Rider o como Carpooler");
                rkm= createReplyKeyboard();
                break;
            //****************************************\\
            //Here the user can correct its mistakes on the registering\\
            //****************************************\\
            case 4:
                responses.add("Ingrese nuevamente sus apellidos");
                responses.add("Recuerde solo puede usar letras del alfabeto mayusculas y minusculas, ademas de espacios.");
                break;
            case 5:
                responses.add("Ingrese nuevamente sus nombres");
                responses.add("Recuerde solo puede usar letras del alfabeto mayusculas y minusculas, ademas de espacios.");
                break;
            //****************************************\\
            //Here starts the carpooler part\\
            //****************************************\\
            case 6:
                responses.add("Para ser carpooler debe agregar algunos datos más");
                responses.add("¿Cuál es su celular?");
                break;
            case 7:
                responses.add("¿Cuál es su número carnet de identidad?");
                break;
            case 8:
                responses.add("Ingrese un numero de celular válido");
                break;
            case 9:
                responses.add("Ingrese un numero de carnet de identidad válido");
                break;
            //****************************************\\
            //Here is the Menu for Carpooler\\
            //****************************************\\
            case 10:
                responses.add("¿Que desea hacer a continuación?");
                rkm= createReplyKeyboardCarpooler();
                break;
            //****************************************\\
            //Here is the registering for the car\\
            //****************************************\\
            case 11:
                responses.add("Para ser un carpooler registre su vehiculo");
                responses.add("¿Cuál es la marca del vehículo?");
                break;
            case 12:
                responses.add("¿Cuál es el modelo?");
                break;
            case 13:
                responses.add("¿Cuál es la placa?");
                break;
            case 14:
                responses.add("¿Cuantos pasajeros puede llevar?");
                break;

        }
        for(String messageText: responses) {
            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(update.getMessage().getChatId())
                    .setText(messageText);
            if(rkm!=null){
                message.setReplyMarkup(rkm);
            }else{
                ReplyKeyboardRemove keyboardMarkupRemove = new ReplyKeyboardRemove();
                message.setReplyMarkup(keyboardMarkupRemove);
            }
            try {
                this.execute(message);

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
