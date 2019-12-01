package com.carpooling.bot.bot;

import com.carpooling.bot.bl.*;
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
    ZoneBl zoneBl;

    @Autowired
    public BotInitializator(BotBl botBl,CarBl carBl,UserBl userBl,PersonBl personBl, ZoneBl zoneBl) {
        this.botBl = botBl;
        this.carBl = carBl;
        this.userBl = userBl;
        this.personBl = personBl;
        this.zoneBl = zoneBl;
    }

    public BotInitializator() {
    }

    @PostConstruct
    public void init() {

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new MainBot(botBl,carBl,userBl,personBl, zoneBl));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
