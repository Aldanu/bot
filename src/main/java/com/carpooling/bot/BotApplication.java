package com.carpooling.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramWebhookBot;

@SpringBootApplication
public class BotApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();
		//CarpoolingBot bot = new CarpoolingBot("949452837:AAHuj-LyPG9VHc1vxIoGRJ7iMIyOoemEXHk");
		SpringApplication.run(BotApplication.class, args);
	}

}
