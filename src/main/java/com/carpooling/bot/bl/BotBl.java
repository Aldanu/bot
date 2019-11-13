package com.carpooling.bot.bl;
import com.carpooling.bot.dao.CpPersonRepository;
import com.carpooling.bot.dao.CpUserRepository;
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
    @Autowired
    public BotBl(CpUserRepository cpUserRepository, CpPersonRepository cpPersonRepository) {
        this.cpUserRepository = cpUserRepository;
        this.cpPersonRepository = cpPersonRepository;
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
            Integer in;
            CpPerson cpPerson;
            CpUser cpUser = cpUserRepository.findByBotUserId(update.getMessage().getFrom().getId().toString());
            int last_conversation = cpUser.getConversationId();
            //What happens when chatbot receives a response to a conversation "last conversation"
            switch (last_conversation){
                case 1:
                    in = cpUser.getPersonId().getPersonId();
                    LOGGER.info("Buscando el id {} en CpPerson",in);
                    cpPerson = cpPersonRepository.findById(in).get();
                    cpPerson.setLastName(update.getMessage().getText());
                    cpPersonRepository.save(cpPerson);
                    cpUser.setConversationId(2);
                    cpUserRepository.save(cpUser);
                    response = 2;
                    break;
                case 2:
                    in = cpUser.getPersonId().getPersonId();
                    LOGGER.info("Buscando el id {} en CpPerson",in);
                    cpPerson = cpPersonRepository.findById(in).get();
                    cpPerson.setFirstName(update.getMessage().getText());
                    cpPersonRepository.save(cpPerson);
                    cpUser.setConversationId(3);
                    cpUserRepository.save(cpUser);
                    response = 3;
                case 3:
                    response = 3;
                    break;
            }

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
}
