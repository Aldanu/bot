package com.carpooling.bot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class BotApplication {

    public static void main(String[] args) {
        //Add this line to initialize bots context
        ApiContextInitializer.init();

        SpringApplication.run(CarpoolingBot.class, args);

    }
}
