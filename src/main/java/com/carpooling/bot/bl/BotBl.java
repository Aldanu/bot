package com.carpooling.bot.bl;
import com.carpooling.bot.bot.MainBot;
import com.carpooling.bot.dao.*;
import com.carpooling.bot.domain.*;
import com.carpooling.bot.dto.Status;
import com.carpooling.bot.bot.responseConversation;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BotBl {
    private static final Logger LOGGER = LoggerFactory.getLogger(BotBl.class);
    private CpUserRepository cpUserRepository;
    private CpPersonRepository cpPersonRepository;
    private CpCarRepository cpCarRepository;
    private CpTravelRepository cpTravelRepository;
    private CpTravelPlaceRepository cpTravelPlaceRepository;
    private UserBl userBl;
    private CarBl carBl;
    private PersonBl personBl;
    private ZoneBl zoneBl;
    private PlaceBl placeBl;
    private TravelBl travelBl;
    private TravelPlaceBl travelPlaceBl;
    @Autowired
    public BotBl(CpUserRepository cpUserRepository, CpPersonRepository cpPersonRepository, CpCarRepository cpCarRepository,
                 CpTravelRepository cpTravelRepository, CpTravelPlaceRepository cpTravelPlaceRepository,UserBl userBl, CarBl carBl, PersonBl personBl,ZoneBl zoneBl,PlaceBl placeBl
                ,TravelBl travelBl,TravelPlaceBl travelPlaceBl) {
        this.cpUserRepository = cpUserRepository;
        this.cpPersonRepository = cpPersonRepository;
        this.cpCarRepository= cpCarRepository;
        this.cpTravelRepository = cpTravelRepository;
        this.cpTravelPlaceRepository = cpTravelPlaceRepository;
        this.userBl = userBl;
        this.carBl = carBl;
        this.personBl = personBl;
        this.zoneBl = zoneBl;
        this.placeBl = placeBl;
        this.travelBl = travelBl;
        this.travelPlaceBl = travelPlaceBl;
    }

    //This method process and update when a user is send a message to the chatbot
    public responseConversation processUpdate(Update update){
        LOGGER.info("Receiving an update from user {}",update);
        int response = 0;
        List<String> options = new ArrayList<>();
        if(isNewUser(update)){
            LOGGER.info("First time using app for: {} ",update.getMessage().getFrom() );
            response = 1;
        }
        else{
            List<CpCar> allCars;
            Boolean validation;
            String newLastName,newFirstName,newCellphone,newCI,newBrand,newModel,newEnrollmentNumber,newPassenger;
            Integer idUser;
            CpCar newCar;
            CpPerson cpPerson;
            CpUser cpUser;
            cpUser = cpUserRepository.findByBotUserId(update.getMessage().getFrom().getId().toString());

            int last_conversation = cpUser.getConversationId();
            //What happens when chatbot receives a response to a conversation "last conversation"
            switch (last_conversation){
                //****************************************\\
                //Here is the initial registering\\
                //****************************************\\
                case 1:
                    idUser = cpUser.getPersonId().getPersonId();
                    LOGGER.info("Buscando el id {} en CpPerson",idUser);
                    cpPerson = cpPersonRepository.findById(idUser).get();
                    newLastName = update.getMessage().getText();
                    //See if the Last name only has alphabetical Letters and spaces
                    validation = isOnlyAlphabeticalCharacters(newLastName);
                    if(validation){
                        cpPerson.setLastName(newLastName);
                        cpPersonRepository.save(cpPerson);
                        response = 2;
                    }
                    else{
                        response = 4;
                    }

                    break;
                case 2:
                    idUser = cpUser.getPersonId().getPersonId();
                    LOGGER.info("Buscando el id {} en CpPerson",idUser);
                    cpPerson = cpPersonRepository.findById(idUser).get();
                    newFirstName = update.getMessage().getText();
                    validation = isOnlyAlphabeticalCharacters(newFirstName);
                    if(validation){
                        cpPerson.setFirstName(newFirstName);
                        cpPersonRepository.save(cpPerson);
                        response = 3;
                    }
                    else{
                        response = 5;
                    }
                    break;
                case 3:
                    idUser = cpUser.getPersonId().getPersonId();
                    LOGGER.info("Buscando el id {} en CpPerson",idUser);
                    cpPerson = cpPersonRepository.findById(idUser).get();
                    response = 3;
                    if(update.getMessage().getText().equals("Carpooler")){
                        //When a user is not a carpooler in the chatbot
                        if(cpPerson.getCarpool()==0){
                            response = 6;
                        }
                        //When a user is a carpooler in the chatbot
                        else{
                            response = 10;
                        }
                        cpPerson.setCarpool(1);
                        cpPersonRepository.save(cpPerson);
                    }
                    if(update.getMessage().getText().equals("Rider")){
                        response=20;
                    }
                    if(update.getMessage().getText().equals("Corregir registro")){
                        response = 4;
                    }
                    break;
                //****************************************\\
                //Here the user can correct its mistakes on the registering\\
                //****************************************\\
                case 4:
                    //Try again to enter Last Name
                    idUser = cpUser.getPersonId().getPersonId();
                    cpPerson = cpPersonRepository.findById(idUser).get();
                    newLastName = update.getMessage().getText();
                    validation = isOnlyAlphabeticalCharacters(newLastName);
                    if(validation){
                        cpPerson.setLastName(newLastName);
                        cpPersonRepository.save(cpPerson);
                        response = 2;
                    }
                    else{

                        response = 4;
                    }
                    break;
                case 5:
                    //Try again to enter First Name
                    idUser = cpUser.getPersonId().getPersonId();
                    cpPerson = cpPersonRepository.findById(idUser).get();
                    newFirstName = update.getMessage().getText();
                    validation = isOnlyAlphabeticalCharacters(newFirstName);
                    if(validation){
                        cpPerson.setFirstName(newFirstName);
                        cpPersonRepository.save(cpPerson);
                        response = 3;
                    }
                    else{
                        response = 5;
                    }
                    break;
                //****************************************\\
                //Here starts the carpooler part\\
                //****************************************\\
                case 6:
                    idUser = cpUser.getPersonId().getPersonId();
                    LOGGER.info("Buscando el id {} en CpPerson",idUser);
                    cpPerson = cpPersonRepository.findById(idUser).get();
                    newCellphone = update.getMessage().getText();
                    if(isValidCellphone(newCellphone)){
                        cpPerson.setCellphoneNumber(newCellphone);
                        cpPersonRepository.save(cpPerson);
                        response = 7;
                    }
                    else{
                        response = 8;
                    }
                    break;
                case 7:
                    idUser = cpUser.getPersonId().getPersonId();
                    LOGGER.info("Buscando el id {} en CpPerson",idUser);
                    cpPerson = cpPersonRepository.findById(idUser).get();
                    newCI = update.getMessage().getText();
                    if(isValidCI(newCI)){
                        cpPerson.setCiNumber(newCI);
                        cpPersonRepository.save(cpPerson);
                        response = 10;
                    }
                    else{
                        response = 9;
                    }
                    break;
                case 8:
                    idUser = cpUser.getPersonId().getPersonId();
                    cpPerson = cpPersonRepository.findById(idUser).get();
                    newCellphone = update.getMessage().getText();
                    if(isValidCellphone(newCellphone)){
                        cpPerson.setCellphoneNumber(newCellphone);
                        cpPersonRepository.save(cpPerson);
                        response = 7;
                    }
                    else{
                        response = 8;
                    }
                    break;
                case 9:
                    idUser = cpUser.getPersonId().getPersonId();
                    cpPerson = cpPersonRepository.findById(idUser).get();
                    newCI = update.getMessage().getText();
                    if(isValidCI(newCI)){
                        cpPerson.setCiNumber(newCI);
                        cpPersonRepository.save(cpPerson);
                        response = 10;
                    }
                    else{
                        response = 9;
                    }
                    break;
                //****************************************\\
                //Here is the Menu for Carpooler\\
                //****************************************\\
                case 10:
                    idUser = cpUser.getPersonId().getPersonId();
                    LOGGER.info("Buscando el id {} en CpPerson",idUser);
                    cpPerson = cpPersonRepository.findById(idUser).get();
                    response = 10;
                    //Here is the menu for the carpooler
                    if(update.getMessage().getText().equals("Registrar Vehículo")){
                        response = 11;
                    }
                    if(update.getMessage().getText().equals("Ver Vehículos")){
                        response = 19;
                    }
                    if(update.getMessage().getText().equals("Registrar Viaje")){
                        LOGGER.info("Registrar Viaje");
                        response = 27;
                    }
                    if(update.getMessage().getText().equals("Ver viajes")){
                        response = 29;
                    }
                    if(update.getMessage().getText().equals("Volver al Menú Principal")){
                        response = 3;
                    }
                    break;
                //****************************************\\
                //Here is the registering for the car\\
                //****************************************\\
                case 11:
                    idUser = cpUser.getPersonId().getPersonId();
                    LOGGER.info("Buscando el id {} en CpPerson",idUser);
                    newBrand = update.getMessage().getText();
                    if(isOnlyAlphaNumeric(newBrand)){
                        newCar = new CpCar();
                        newCar.setPersonId(cpUser.getPersonId());
                        newCar.setBrand(newBrand);
                        newCar.setEnrollmentNumber("NULL");
                        newCar.setModel("NULL");
                        newCar.setCapacity(0);
                        newCar.setStatus(1);
                        newCar.setTxHost("localhost");
                        newCar.setTxUser("admin");
                        newCar.setTxDate(new Date());
                        cpCarRepository.save(newCar);
                        response = 12;
                    }
                    else{
                        response = 15;
                    }
                    break;
                case 12:
                    idUser = cpUser.getPersonId().getPersonId();
                    LOGGER.info("Buscando el id {} en CpPerson",idUser);
                    newModel = update.getMessage().getText();
                    if(isOnlyAlphaNumeric(newModel)){
                        allCars = cpCarRepository.findAll();
                        newCar = getLastCar(allCars,idUser);
                        newCar.setModel(newModel);
                        cpCarRepository.save(newCar);
                        response = 13;
                    }
                    else{
                        response = 16;
                    }
                    break;
                case 13:
                    idUser = cpUser.getPersonId().getPersonId();
                    LOGGER.info("Buscando el id {} en CpPerson",idUser);
                    newEnrollmentNumber = update.getMessage().getText();
                    if(isEnrollmentNumberValid(newEnrollmentNumber)){
                        allCars = cpCarRepository.findAll();
                        newCar = getLastCar(allCars,idUser);
                        newCar.setEnrollmentNumber(newEnrollmentNumber);
                        cpCarRepository.save(newCar);
                        response = 14;
                    }
                    else{
                        response = 17;
                    }
                    break;
                case 14:
                    idUser = cpUser.getPersonId().getPersonId();
                    LOGGER.info("Buscando el id {} en CpPerson",idUser);
                    newPassenger = update.getMessage().getText();
                    if(isOnlyNumbers(newPassenger)) {
                        allCars = cpCarRepository.findAll();
                        newCar = getLastCar(allCars, idUser);
                        newCar.setCapacity(Integer.parseInt(newPassenger));
                        cpCarRepository.save(newCar);
                        response = 10;
                    }
                    else{
                        response = 18;
                    }
                    break;
                case 15:
                    idUser = cpUser.getPersonId().getPersonId();
                    newBrand = update.getMessage().getText();
                    if(isOnlyAlphaNumeric(newBrand)){
                        newCar = new CpCar();
                        newCar.setPersonId(cpUser.getPersonId());
                        newCar.setBrand(newBrand);
                        newCar.setEnrollmentNumber("NULL");
                        newCar.setModel("NULL");
                        newCar.setCapacity(0);
                        newCar.setStatus(1);
                        newCar.setTxHost("localhost");
                        newCar.setTxUser("admin");
                        newCar.setTxDate(new Date());
                        cpCarRepository.save(newCar);
                        response = 12;
                    }
                    else{
                        response = 15;
                    }
                    break;
                case 16:
                    idUser = cpUser.getPersonId().getPersonId();
                    newModel = update.getMessage().getText();
                    if(isOnlyAlphaNumeric(newModel)){
                        allCars = cpCarRepository.findAll();
                        newCar = getLastCar(allCars,idUser);
                        newCar.setModel(newModel);
                        cpCarRepository.save(newCar);
                        response = 13;
                    }
                    else{
                        response = 16;
                    }
                    break;
                case 17:
                    idUser = cpUser.getPersonId().getPersonId();
                    newEnrollmentNumber = update.getMessage().getText();
                    if(isEnrollmentNumberValid(newEnrollmentNumber)){
                        allCars = cpCarRepository.findAll();
                        newCar = getLastCar(allCars,idUser);
                        newCar.setEnrollmentNumber(newEnrollmentNumber);
                        cpCarRepository.save(newCar);
                        response = 14;
                    }
                    else{
                        response = 17;
                    }
                    break;
                case 18:
                    idUser = cpUser.getPersonId().getPersonId();
                    newPassenger = update.getMessage().getText();
                    if(isOnlyNumbers(newPassenger)) {
                        allCars = cpCarRepository.findAll();
                        newCar = getLastCar(allCars, idUser);
                        newCar.setCapacity(Integer.parseInt(newPassenger));
                        cpCarRepository.save(newCar);
                        response = 10;
                    }
                    else{
                        response = 18;
                    }
                    break;
                case 19:
                    response = 10;
                    break;
                case 20:
                    idUser = cpUser.getPersonId().getPersonId();
                    LOGGER.info("Buscando el id {} en CpPerson",idUser);
                    cpPerson = cpPersonRepository.findById(idUser).get();
                    response = 20;
                    //Here is the menu for the carpooler
                    if(update.getMessage().getText().equals("Buscar Viaje")){
                        response = 21;
                    }
                    if(update.getMessage().getText().equals("Ver Viaje")){
                        response = 22;
                    }
                    if(update.getMessage().getText().equals("Cancelar Viajes")){
                        response = 23;
                    }
                    if(update.getMessage().getText().equals("Volver al Menú Principal")){
                        response = 3;
                    }else{
                        response =30;
                    }
                    break;
                case 21:
                    response=30;
                    if(isOnlyNumbers(update.getMessage().getText())){
                        response = 23;
                    }
                    break;
                case 22:
                    response = 20;
                    break;
                case 23:
                    response = 30;
                    if(isOnlyNumbers(update.getMessage().getText())){
                        response = 24;
                    }
                    break;
                case 24:
                    idUser = cpUser.getPersonId().getPersonId();
                    LOGGER.info("Buscando el id {} en CpPerson",idUser);
                    cpPerson = cpPersonRepository.findById(idUser).get();
                    response = 24;
                    //Here is the menu for the carpooler
                    if(update.getMessage().getText().equals("Si")) {
                        response = 25;
                    }else if(update.getMessage().getText().equals("No")){
                        response = 26;
                    }else{
                        response=30;
                    }
                    break;
                case 25:
                    LOGGER.info("Se confirme el viaje y se vuelve al menu");
                    response = 20;
                    break;
                case 26:
                    LOGGER.info("Se cancela el viaje y se vuelve al menu");
                    response = 20;
                    break;
                case 27:
                    LOGGER.info("Registering an empty travel");
                    //Set carName without spaces to search this in DB
                    String carName = update.getMessage().getText();
                    carName = carName.replace(" ","");
                    cpUser = userBl.findUserByTelegramUserId(update);
                    cpPerson = personBl.findPersonById(cpUser.getPersonId().getPersonId());
                    List<CpCar> allCarsList = carBl.all();
                    CpCar carForTravel = null;
                    for(CpCar car: allCarsList){
                        if(car.getPersonId().getPersonId() == cpPerson.getPersonId()){
                            String probCar = car.toStringOption().replace(" ","");
                            if(probCar.equals(carName)){
                                carForTravel = carBl.findById(car.getCarId());
                            }
                        }
                    }
                    if(carForTravel == null){
                        response = 27;
                    }
                    else{
                        CpTravel cpTravel = new CpTravel();
                        cpTravel.setCarId(carForTravel);
                        cpTravel.setDepartureTime("-");
                        cpTravel.setCost(new BigDecimal("0.00"));
                        cpTravel.setNumberPassengers(0);
                        cpTravel.setPetFriendly(0);
                        cpTravel.setStatus(1);
                        cpTravel.setTxUser("admin");
                        cpTravel.setTxHost("localhost");
                        cpTravel.setTxDate(new Date());
                        cpTravelRepository.save(cpTravel);
                        response = 28;
                    }
                    break;
                case 28:
                    CpZone selectedZone = zoneBl.findByName(update.getMessage().getText());
                    List<CpPlace> placesZone = placeBl.findByZone(selectedZone);
                    for(CpPlace place:placesZone){
                        options.add(place.toStringOption());
                    }
                    response = 31;
                    break;
                case 29:

                    response = 10;
                    break;
                case 30:
                    LOGGER.info("Se notifica Entrada no valida y se vuelve al menu");
                    response = 20;
                    break;
                case 31:
                    CpPlace startPlace = placeBl.getPlaceByName(update.getMessage().getText());
                    idUser = cpUser.getPersonId().getPersonId();
                    cpPerson = cpPersonRepository.findById(idUser).get();
                    List<CpTravel> travels = cpTravelRepository.findAll();
                    CpTravel currenTravel = travelBl.getLastTravel(travels,cpPerson);
                    for(CpPlace place:placeBl.all()){
                        options.add(place.toStringOption());
                    }
                    if(startPlace!=null && currenTravel!=null) {
                        CpTravelPlace cpTravelPlace = new CpTravelPlace();
                        cpTravelPlace.setPlaceId(startPlace);
                        cpTravelPlace.setTravelId(currenTravel);
                        cpTravelPlace.setposition(1);
                        cpTravelPlace.setStatus(1);
                        cpTravelPlace.setTxHost("localhost");
                        cpTravelPlace.setTxUser("admin");
                        cpTravelPlace.setTxDate(new Date());
                        cpTravelPlaceRepository.save(cpTravelPlace);
                        options.add("Terminar");
                        response=32;
                    }
                    //LIST ALL PLACES WITHOUT ORDER
                    else{
                        response = 31;
                    }
                    break;
                case 32:
                    options.clear();
                    CpPlace placeSelected = placeBl.getPlaceByName(update.getMessage().getText());
                    idUser = cpUser.getPersonId().getPersonId();
                    cpPerson = cpPersonRepository.findById(idUser).get();
                    List<CpTravel> travelList = cpTravelRepository.findAll();
                    CpTravel lastTravel = travelBl.getLastTravel(travelList,cpPerson);
                    int lastPosition = travelPlaceBl.getLastPosition(lastTravel);
                    if(update.getMessage().getText().equals("Terminar")){
                        response = 10;
                    }
                    else{
                        for(CpPlace place:placeBl.all()){
                            options.add(place.toStringOption());
                        }
                        options.add("Terminar");
                        response = 32;
                        //STORE IN DB a new stop
                        CpTravelPlace travelPlace = new CpTravelPlace();
                        travelPlace.setposition(lastPosition);
                        travelPlace.setTxUser("admin");
                        travelPlace.setTxHost("localhost");
                        travelPlace.setTxDate(new Date());
                        travelPlace.setTravelId(lastTravel);
                        travelPlace.setPlaceId(placeSelected);
                        travelPlace.setStatus(1);
                        cpTravelPlaceRepository.save(travelPlace);
                    }
            }
            cpUser.setConversationId(response);
            cpUserRepository.save(cpUser);
        }
        responseConversation result = new responseConversation(response,options);
        return result;
    }
    private boolean isNewUser(Update update){
        boolean response = false;
        User user = update.getMessage().getFrom();
        CpUser cpUser = cpUserRepository.findByBotUserId(user.getId().toString());
        if (cpUser == null) {
            CpPerson cpPerson = new CpPerson();
            cpPerson.setFirstName(user.getFirstName());

            if (user.getLastName() == null) {
                cpPerson.setLastName("-");
            } else {
                cpPerson.setLastName(user.getLastName());
            }
            cpPerson.setStatus(Status.ACTIVE.getStatus());
            cpPerson.setCarpool(0);//0 is for a not carpooler user
            cpPerson.setTxHost("localhost");
            cpPerson.setTxUser("admin");
            cpPerson.setTxDate(new Date());
            cpPersonRepository.save(cpPerson);
            cpUser = new CpUser();
            cpUser.setBotUserId(user.getId().toString());
            cpUser.setChatUserId(update.getMessage().getChatId().toString());
            cpUser.setPersonId(cpPerson);
            cpUser.setConversationId(1);
            cpUser.setTxHost("localhost");
            cpUser.setTxUser("admin");
            cpUser.setTxDate(new Date());
            cpUserRepository.save(cpUser);
            response = true;
        }
        return response;
    }
    private boolean isOnlySpaces(String text){
        Boolean validation = true;
        for(int i=0;i<text.length();i++){
            char ch = text.charAt(i);
            if(ch != ' '){
                validation = false;
            }
            break;
        }
        return validation;
    }
    private boolean isOnlyAlphabeticalCharacters(String text){
        Boolean validation = true;
        for(int i=0;i<text.length();i++){
            char ch = text.charAt(i);
            if(!Character.isLetter(ch) && ch != ' '){
                validation = false;
                break;
            }
        }
        if(isOnlySpaces(text)){
            validation = false;
        }
        return validation;
    }
    private boolean isValidCellphone(String text){
        Boolean validation = true;
        for(int i=0;i<text.length();i++){
            char ch = text.charAt(i);
            if(!Character.isDigit(ch)){
                validation = false;
            }
        }
        if(isOnlySpaces(text)){
            validation = false;
        }
        if(text.length()!=8){
            validation = false;
        }
        if(text.charAt(0)!='6' && text.charAt(0)!='7'){
            validation = false;
        }
        return validation;
    }
    private boolean isValidCI(String text){
        //7-8 numbers or 7-8 numbers plus a alphabetical character
        Boolean validation = true;
        int lenght = text.length();
        char lastCharacter = text.charAt(lenght-1);
        if(Character.isDigit(lastCharacter) || Character.isAlphabetic(lastCharacter)){
            for(int i=0;i<lenght-1;i++){
                char ch = text.charAt(i);
                if(!Character.isDigit(ch)){
                    validation = false;
                    break;
                }
            }
        }
        else{
            validation = false;
        }
        if(isOnlySpaces(text)){
            validation = false;
        }
        return validation;
    }
    private CpCar getLastCar(List<CpCar> all, Integer idUser){
        CpCar lastCar = null;
        for(CpCar car: all){
            if(car.getPersonId().getPersonId() == idUser && car.getStatus()==1){
                lastCar = car;
            }
        }
        return  lastCar;
    }
    private boolean isOnlyAlphaNumeric(String text){
        boolean validation = true;
        for(int i=0;i<text.length();i++){
            char ch = text.charAt(i);
            if(!Character.isAlphabetic(ch) && !Character.isDigit(ch)){
                validation = false;
                break;
            }
        }
        return validation;
    }
    private boolean isEnrollmentNumberValid(String text){
        boolean validation = true;
        int lenght = text.length();
        if(lenght==8){
            if(text.charAt(4) != '-'){
                validation = false;
            }
            for(int i=0;i<4;i++){
                if(!Character.isDigit(text.charAt(i))){
                    validation = false;
                    break;
                }
            }
            for(int i=5;i<8;i++){
                if(!Character.isAlphabetic(text.charAt(i))){
                    validation = false;
                    break;
                }
            }
        }
        else{
            validation = false;
        }
        return  validation;
    }
    private  boolean isOnlyNumbers(String text){
        boolean validation = true;
        for(int i=0;i<text.length();i++){
            if(!Character.isDigit(text.charAt(i))){
                validation = false;
                break;
            }
        }
        return validation;
    }
}