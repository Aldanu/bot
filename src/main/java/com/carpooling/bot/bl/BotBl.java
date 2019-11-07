package com.carpooling.bot.bl;
import com.carpooling.bot.dao.CpPersonRepository;
import com.carpooling.bot.dao.CpUserRepository;
import com.carpooling.bot.domain.CpUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.ArrayList;
import java.util.List;

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
    public int processUpdate(Update update,int currChat){
        LOGGER.info("Receiving an update from user {}",update);
        int response = 0;
        if(isNewUser(update.getMessage().getFrom()) && currChat==0){
            LOGGER.info("First time using app for: {} ",update.getMessage().getFrom() );
            response = 1;
        }
        else{
            response = 2;
        }
        return response;
    }
    private boolean isNewUser(User user){
        boolean response = false;
        CpUser cpUser = cpUserRepository.findByBotUserId(user.getId().toString());
        if(cpUser==null){
            response = true;
        }
        return response;
    }
}
