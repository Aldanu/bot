package com.carpooling.bot.bl;
import com.carpooling.bot.dao.CpCarRepository;
import com.carpooling.bot.dao.CpPersonRepository;
import com.carpooling.bot.dao.CpUserRepository;
import com.carpooling.bot.domain.CpCar;
import com.carpooling.bot.domain.CpPerson;
import com.carpooling.bot.domain.CpUser;
import com.carpooling.bot.dto.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BotBl {
    private static final Logger LOGGER = LoggerFactory.getLogger(BotBl.class);
    private CpUserRepository cpUserRepository;
    private CpPersonRepository cpPersonRepository;
    private CpCarRepository cpCarRepository;
    @Autowired
    public BotBl(CpUserRepository cpUserRepository, CpPersonRepository cpPersonRepository, CpCarRepository cpCarRepository) {
        this.cpUserRepository = cpUserRepository;
        this.cpPersonRepository = cpPersonRepository;
        this.cpCarRepository= cpCarRepository;
    }

    //This method process and update when a user is send a message to the chatbot
    public int processUpdate(Update update){
        LOGGER.info("Receiving an update from user {}",update);
        int response = 0;
        if(isNewUser(update)){
            LOGGER.info("First time using app for: {} ",update.getMessage().getFrom() );
            response = 1;
        }
        else{
            Integer idUser;
            CpCar cpCar;
            CpPerson cpPerson;
            CpUser cpUser = cpUserRepository.findByBotUserId(update.getMessage().getFrom().getId().toString());
            int last_conversation = cpUser.getConversationId();
            //What happens when chatbot receives a response to a conversation "last conversation"
            switch (last_conversation){
                case 1:
                    idUser = cpUser.getPersonId().getPersonId();
                    LOGGER.info("Buscando el id {} en CpPerson",idUser);
                    cpPerson = cpPersonRepository.findById(idUser).get();
                    String newLastName = update.getMessage().getText();
                    //See if the Last name only has alphabetical Letters and spaces
                    Boolean validation = isOnlyAlphabeticalCharacters(newLastName);
                    if(validation){
                        cpPerson.setLastName(newLastName);
                        cpPersonRepository.save(cpPerson);
                        cpUser.setConversationId(2);
                        cpUserRepository.save(cpUser);
                        response = 2;
                    }
                    else{
                        cpUser.setConversationId(4);
                        cpUserRepository.save(cpUser);
                        response = 4;
                    }

                    break;
                case 2:
                    idUser = cpUser.getPersonId().getPersonId();
                    LOGGER.info("Buscando el id {} en CpPerson",idUser);
                    cpPerson = cpPersonRepository.findById(idUser).get();
                    String newFirstName = update.getMessage().getText();
                    validation = isOnlyAlphabeticalCharacters(newFirstName);
                    if(validation){
                        cpPerson.setFirstName(newFirstName);
                        cpPersonRepository.save(cpPerson);
                        cpUser.setConversationId(3);
                        cpUserRepository.save(cpUser);
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
                        cpPerson.setCarpool(1);
                        cpPersonRepository.save(cpPerson);
                        if(cpPerson.getCellphoneNumber()==null){
                            response = 6;
                        }else{
                            response = 10;
                        }
                    }
                    break;
                case 6:
                    idUser = cpUser.getPersonId().getPersonId();
                    LOGGER.info("Buscando el id {} en CpPerson",idUser);
                    cpPerson = cpPersonRepository.findById(idUser).get();
                    cpPerson.setCellphoneNumber(update.getMessage().getText());
                    cpPersonRepository.save(cpPerson);
                    response = 7;
                    break;
                case 7:
                    idUser = cpUser.getPersonId().getPersonId();
                    LOGGER.info("Buscando el id {} en CpPerson",idUser);
                    cpPerson = cpPersonRepository.findById(idUser).get();
                    cpPerson.setCiNumber(update.getMessage().getText());
                    cpPersonRepository.save(cpPerson);
                    response = 10;
                    break;
                case 10:
                    idUser = cpUser.getPersonId().getPersonId();
                    LOGGER.info("Buscando el id {} en CpPerson",idUser);
                    cpPerson = cpPersonRepository.findById(idUser).get();
                    response = 10;
                    //Here is the menu for the carpooler
                    if(update.getMessage().getText().equals("Registrar Vehículo")){
                        response = 11;
                    }
                    if(update.getMessage().getText().equals("Registrar Viaje")){
                        response = 10;
                    }
                    if(update.getMessage().getText().equals("Cambiar a Rider")){
                        cpPerson.setCarpool(0);
                        cpPersonRepository.save(cpPerson);
                        response = 3;
                    }
                    if(update.getMessage().getText().equals("Ver viajes")){
                        response = 10;
                    }
                    break;
                case 11:
                    idUser = cpUser.getPersonId().getPersonId();
                    LOGGER.info("Buscando el id {} en CpPerson",idUser);
                    //cpCar.setBrand(update.getMessage().getText());
                    //Here should be register the data obtained to the database for the car brand
                    response = 12;
                    break;
                case 12:
                    idUser = cpUser.getPersonId().getPersonId();
                    LOGGER.info("Buscando el id {} en CpPerson",idUser);
                    //cpCar.setModel(update.getMessage().getText());
                    //Here should be register the data obtained to the database for the car model
                    response = 13;
                    break;
                case 13:
                    idUser = cpUser.getPersonId().getPersonId();
                    LOGGER.info("Buscando el id {} en CpPerson",idUser);
                    //cpCar.setEnrollmentNumber(update.getMessage().getText());
                    //Here should be register the data obtained to the database for the car EnrollmentNumber
                    response = 14;
                    break;
                case 14:
                    idUser = cpUser.getPersonId().getPersonId();
                    LOGGER.info("Buscando el id {} en CpPerson",idUser);
                    //cpCar.setCapacity(update.getMessage().getText());
                    //Here should be register the data obtained to the database for the car capacity
                    response = 10;
                    break;
            }
            cpUser.setConversationId(response);
            cpUserRepository.save(cpUser);
        }
        return response;
    }
    private boolean isNewUser(Update update){
        boolean response = false;
        User user = update.getMessage().getFrom();
        CpUser cpUser = cpUserRepository.findByBotUserId(user.getId().toString());
        if(cpUser==null){
            CpPerson cpPerson = new CpPerson();
            cpPerson.setFirstName(user.getFirstName());

            if(user.getLastName() == null){
                cpPerson.setLastName("-");
            }
            else{
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
    private boolean isOnlyAlphabeticalCharacters(String text){
        Boolean validation = true;
        for(int i=0;i<text.length();i++){
            char ch = text.charAt(i);
            if(!Character.isLetter(ch) && ch != ' '){
                validation = false;
                break;
            }
        }
        return validation;
    }
}