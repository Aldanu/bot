package com.carpooling.bot.bl;

import com.carpooling.bot.CarpoolingBot;
import com.carpooling.bot.dao.CpCarRepository;
import com.carpooling.bot.dao.CpPersonRepository;
import com.carpooling.bot.domain.CpCar;
import com.carpooling.bot.dto.CarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarBl {
    CpCarRepository cpCarRepository;

    public CarBl(){
        cpCarRepository = null;
    }
    @Autowired
    public CarBl(CpCarRepository cpCarRepository) {
        this.cpCarRepository = cpCarRepository;
    }
    public List<CpCar> all(){
        List<CpCar> carList = new ArrayList<CpCar>();
        List<CpCar> all = cpCarRepository.findAll();
        for(CpCar car: all){
            if(car.getStatus() == 1){
                carList.add(car);
            }
        }
        return carList;
    }
    public CpCar findById(Integer id){
        CpCar car = null;
        car = cpCarRepository.findById(id).get();
        return car;
    }
    public int carRegister(String message_text, long chat_id, int step, CarDto car, CarpoolingBot carpoolingBot) {
        String text;
        switch (step) {
            case 0:
                carpoolingBot.sendMessage(chat_id, "Para ser Carpooler debe registrar su vehiculo");
                text= "Cual es la marca?";
                step = 1;
                carpoolingBot.sendMessage(chat_id, text); // Sending our message object to user
                break;
            case 1:
                CarpoolingBot.cardto.setBrand(message_text);
                text= "Cual es el modelo?";
                step = 2;
                carpoolingBot.sendMessage(chat_id, text); // Sending our message object to user
                break;
            case 2:
                CarpoolingBot.cardto.setModel(message_text);
                text="Cual es la placa?";
                step = 3;
                carpoolingBot.sendMessage(chat_id, text);

                break;
            case 3:
                CarpoolingBot.cardto.setEnrollmentNumber(message_text);
                text="Cuantos pasajeros puede llevar?";
                step = 4;
                carpoolingBot.sendMessage(chat_id, text);
                break;
            case 4:
                CarpoolingBot.cardto.setCapacity(Integer.parseInt(message_text));
                String val = "Marca = " + car.getBrand() + "\nModelo = " + car.getModel() + "\nPlaca = " + car.getEnrollmentNumber() + "\nAsientos = " + car.getCapacity();
                step = 0;
                text="Registro exitoso con los siguientes datos: \n" + val;
                carpoolingBot.sendMessage(chat_id, text);
                break;
        }
        return step;
    }



}
