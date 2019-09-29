package com.carpooling.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class BotApplication {
	//ApiContextInitializer.init();
	public static void main(String[] args) {
		SpringApplication.run(BotApplication.class, args);
	}

}
