package com.carpooling.bot.bot;


import com.carpooling.bot.CarpoolingBot;
import com.carpooling.bot.bl.*;
import com.carpooling.bot.domain.*;
import com.carpooling.bot.dto.CarDto;
import com.carpooling.bot.dto.PersonDto;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
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
    private PersonBl personBl;
    private ZoneBl zoneBl;
    private TravelBl travelBl;
    //This both classes should be changed to an array in order to allow multiuser saving data
    public static CarDto cardto=new CarDto();
    public static PersonDto personDto=new PersonDto();
    public static CpPerson cpPerson = new CpPerson();
    public static CpUser cpUser = new CpUser();
    public static CpZone cpZone = new CpZone();
    public static CpTravel cpTravel = new CpTravel();
    //The array gets the data from the user to manage their conversation
    //The LONG has 4 values, chat id, type of user, conversation, and step of the conversation
    private ArrayList<Long[]> data=new ArrayList<>();
    List<String> options = new ArrayList<>();

    private final static Logger LOGGER = Logger.getLogger(CarpoolingBot.class.getName());

    public MainBot(BotBl botBl, CarBl carBl, UserBl userBl,PersonBl personBl, ZoneBl zoneBl, TravelBl travelBl){
        this.botBl = botBl;
        this.carBl = carBl;
        this.userBl = userBl;
        this.personBl = personBl;
        this.zoneBl = zoneBl;
        this.travelBl = travelBl;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            responseConversation action = botBl.processUpdate(update);
            //switch moved to the response function
            response(action, update);
        }else if (update.hasCallbackQuery()) {
            responseConversation action = botBl.processUpdate(update);
            //switch moved to the response function
            response(action, update);
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
    private ReplyKeyboardMarkup createReplyKeyboardConfirmation() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Create a keyboard row
        KeyboardRow row = new KeyboardRow();
        // Set each button, you can also use KeyboardButton objects if you need something else than text
        row.add("Si");
        // Add the first row to the keyboard
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("No");
        keyboard.add(row);

        // Set the keyboard to the markup
        keyboardMarkup.setKeyboard(keyboard);
        // Add it to the message
        return keyboardMarkup;
    }
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
    private ReplyKeyboardMarkup createOkMenu(){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Create a keyboard row
        KeyboardRow row = new KeyboardRow();
        // Set each button, you can also use KeyboardButton objects if you need something else than text
        row.add("OK");
        // Add the first row to the keyboard
        keyboard.add(row);
        // Create another keyboard row
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

    private ReplyKeyboardMarkup createReplyKeyboardRider() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Create a keyboard row
        KeyboardRow row = new KeyboardRow();
        // Set each button, you can also use KeyboardButton objects if you need something else than text

        row.add("Buscar Viaje");
        keyboard.add(row);
        // Create another keyboard row
        row = new KeyboardRow();
        // Set each button for the second line
        row.add("Ver Viaje");
        // Add the second row to the keyboard
        keyboard.add(row);
        row = new KeyboardRow();
        // Set each button for the second line
        row.add("Eliminar Viajes");
        // Add the second row to the keyboard
        keyboard.add(row);
        // Set the keyboard to the markup
        row = new KeyboardRow();
        // Set each button for the second line
        row.add("Volver al Menú Principal");
        // Add the second row to the keyboard
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        // Add it to the message
        return keyboardMarkup;
    }
    private ReplyKeyboardMarkup createReplyKeyboardOptions(List<String> options){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Create a keyboard row
        KeyboardRow row = new KeyboardRow();
        for(String option: options){
            row.add(option);
            keyboard.add(row);
            // Create another keyboard row
            row = new KeyboardRow();
        }
        keyboardMarkup.setKeyboard(keyboard);
        // Add it to the message
        return keyboardMarkup;
    }

    private ReplyKeyboardMarkup createReplyKeyboardTravels(List<String> options){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Create a keyboard row
        KeyboardRow row = new KeyboardRow();
        for(String option: options){
            row.add(option);
            keyboard.add(row);
            // Create another keyboard row
            row = new KeyboardRow();
        }
        keyboardMarkup.setKeyboard(keyboard);
        // Add it to the message
        return keyboardMarkup;
    }

    public void response(responseConversation action, Update update){
        int conversation = action.getConversation();
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
                responses.add("Iniciando Registro de Vehiculo");
                responses.add("¿Cuál es la marca del vehículo?");
                break;
            case 12:
                responses.add("¿Cuál es el modelo?");
                break;
            case 13:
                responses.add("¿Cuál es la placa? Bajo el siguiente formato NNNN-LLL");
                responses.add("N representa un numero y L una letra");
                responses.add("Si su placa tiene menos de 4 numeros completelo con 0 a la izquierda");
                break;
            case 14:
                responses.add("¿Cuantos pasajeros puede llevar?");
                break;
            case 15:
                responses.add("Ingresa una marca válida, solo debe contener letras y números");
                break;
            case 16:
                responses.add("Ingrese un modelo valido, solo debe contener letras y numeros");
                break;
            case 17:
                responses.add("Ingrese una placa valida bajo el siguiente formato NNNN-LLL");
                responses.add("N representa un numero y L una letra");
                responses.add("Si su placa tiene menos de 4 numeros completelo con 0 a la izquierda");
                break;
            case 18:
                responses.add("Ingrese un numero de pasajeros correcto");
                break;
            case 19:
                responses.add("Listado de tus vehiculos");
                cpUser = userBl.findUserByTelegramUserId(update);
                cpPerson = personBl.findPersonById(cpUser.getPersonId().getPersonId());
                List<CpCar> all = carBl.all();
                for(CpCar car: all){
                    if(car.getPersonId().getPersonId() == cpPerson.getPersonId()){
                        responses.add(car.toString());
                    }
                }
                rkm= createOkMenu();
                break;
            //****************************************\\
            //Here is the Menu for Rider\\
            //****************************************\\
            case 20:
                responses.add("¿Que desea hacer a continuación?");
                rkm= createReplyKeyboardRider();
                break;
            case 21:
                responses.add("De donde va a partir?");
                options = zoneOptions(options);
                rkm = createReplyKeyboardOptions(options);
                responses.add("Elija la zona de la que partirá");
                break;
            case 22:
                responses.add("Esta es su lista de viajes proximos");
                rkm= createOkMenu();
                break;
            case 23:
                String zone = update.getMessage().getText();
                responses.add("Estos viajes estan disponibles para la zona "+zone);
                responses.add("Presione el numero del viaje al que desea registrarse");
                //options = travelOptions(options, zone);
                break;
            case 24:
                //if(update.getCallbackQuery()!=null){
                    //String call_data = update.getCallbackQuery().getData();
                    //responses.add("El viaje escogido fue: "+call_data);
                    responses.add("Confirmar Viaje?");
                    rkm= createReplyKeyboardConfirmation();
                /*}else{
                    responses.add("Opcion no valida");
                    rkm= createOkMenu();
                }*/
                break;
            case 25:
                responses.add("Usted confirmo su viaje");
                rkm= createOkMenu();
                break;
            case 26:
                responses.add("Usted cancelo el viaje");
                rkm= createOkMenu();
                break;
            case 27:
                responses.add("Selecciona un automovil");
                cpUser = userBl.findUserByTelegramUserId(update);
                cpPerson = personBl.findPersonById(cpUser.getPersonId().getPersonId());
                List<CpCar> allCars = carBl.all();
                options = new ArrayList<>();
                for(CpCar car: allCars){
                    if(car.getPersonId().getPersonId() == cpPerson.getPersonId()){
                        options.add(car.toStringOption());
                    }
                }
                rkm = createReplyKeyboardOptions(options);
                LOGGER.info("Terminando Caso 27");
                break;
            case 28:
                responses.add("Selecciona la zona de partida");
                options = zoneOptions(options);
                rkm = createReplyKeyboardOptions(options);
                LOGGER.info("Terminando caso 28");
                break;
            case 29:
                responses.add("Estos son sus viajes activos");
                break;
            case 30:
                responses.add("Respuesta no valida");
                rkm=createOkMenu();
                break;
            case 31:
                responses.add("Seleccione un lugar de partida");
                rkm = createReplyKeyboardOptions(action.getOptions());
                LOGGER.info("Creando Lugares por cierta zona");
                break;
            case 32:
                responses.add("Ingrese punto de parada ");
                rkm = createReplyKeyboardOptions(action.getOptions());
                LOGGER.info("Mostrando todos los lugares");
                break;
            case 33:
                responses.add("Ingrese Fecha y Hora de partida en el formato DD/MM/YYYY HH:MM");
                break;
            case 34:
                responses.add("Ingrese el costo del viaje si sera gratis ingrese 0.00");
                responses.add("Procure siempre colocar dos numeros despues del punto decimal");
                responses.add("Por ejemplo si su costo es 1 boliviano ingrese 1.00");
                break;
            case 35:
                responses.add("Ingrese el numero de pasajeros que desea llevar");
                break;
            case 36:
                responses.add("Usted acepta mascotas?");
                rkm = createReplyKeyboardOptions(action.getOptions());
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

    private List<String> zoneOptions(List<String> options){
        options.clear();
        List<CpZone> allZone = zoneBl.all();
        for(CpZone zone: allZone){
            options.add(zone.getName());
        }

        return options;
    }

    private List<String> travelOptions(List<String> options, String zone){
        List<CpTravel> allTravel = travelBl.all();
        for(CpTravel travel: allTravel){
            options.add(travel.getDescription());
        }
        return options;
    }
}
