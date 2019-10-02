package com.carpooling.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class BotApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();
		SpringApplication.run(BotApplication.class, args);
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new CarpoolingBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
	}

}
