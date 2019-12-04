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
    TravelBl travelBl;

    @Autowired
    public BotInitializator(BotBl botBl,CarBl carBl,UserBl userBl,PersonBl personBl, ZoneBl zoneBl, TravelBl travelBl) {
        this.botBl = botBl;
        this.carBl = carBl;
        this.userBl = userBl;
        this.personBl = personBl;
        this.zoneBl = zoneBl;
        this.travelBl = travelBl;
    }

    public BotInitializator() {
    }

    @PostConstruct
    public void init() {

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new MainBot(botBl,carBl,userBl,personBl, zoneBl, travelBl));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
