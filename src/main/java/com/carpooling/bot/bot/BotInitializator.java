package com.carpooling.bot.bot;

import com.carpooling.bot.bl.CarBl;
import com.carpooling.bot.bl.UserBl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

@Component
public class BotInitializator {
    CarBl carBl;
    UserBl userBl;

    @Autowired
    public BotInitializator(CarBl carBl,UserBl userBl) {
        this.carBl = carBl;
        this.userBl = userBl;
    }

    public BotInitializator() {
    }

    @PostConstruct
    public void init() {

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new MainBot(carBl,userBl));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}