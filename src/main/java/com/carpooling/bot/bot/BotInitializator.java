package com.carpooling.bot.bot;

import com.carpooling.bot.bl.BotBl;
import com.carpooling.bot.bl.CarBl;
import com.carpooling.bot.bl.PersonBl;
import com.carpooling.bot.bl.UserBl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

@Component
public class BotInitializator {
    BotBl botBl;
    CarBl carBl;
    UserBl userBl;
    PersonBl personBl;

    @Autowired
    public BotInitializator(BotBl botBl,CarBl carBl,UserBl userBl,PersonBl personBl) {
        this.botBl = botBl;
        this.carBl = carBl;
        this.userBl = userBl;
        this.personBl = personBl;
    }

    public BotInitializator() {
    }

    @PostConstruct
    public void init() {

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new MainBot(botBl,carBl,userBl,personBl));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
