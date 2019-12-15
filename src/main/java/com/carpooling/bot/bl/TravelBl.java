package com.carpooling.bot.bl;

import com.carpooling.bot.dao.CpCarRepository;
import com.carpooling.bot.dao.CpTravelRepository;
import com.carpooling.bot.domain.CpCar;
import com.carpooling.bot.domain.CpPerson;
import com.carpooling.bot.domain.CpTravel;
import com.carpooling.bot.domain.CpTravelSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Service
@Transactional
public class TravelBl {
    CpTravelRepository cpTravelRepository;
    CpCarRepository cpCarRepository;
    TravelPlaceBl travelPlaceBl;
    Validator validator = new Validator();
    public TravelBl(){
        cpTravelRepository = null;
    }
    @Autowired
    public TravelBl(CpTravelRepository cpTravelRepository, CpCarRepository cpCarRepository,TravelPlaceBl travelPlaceBl) {
        this.travelPlaceBl = travelPlaceBl;
        this.cpTravelRepository = cpTravelRepository;
        this.cpCarRepository = cpCarRepository;
    }
    public List<CpTravel> all(){
        List<CpTravel> travelList = new ArrayList<>();
        List<CpTravel> all = cpTravelRepository.findAll();
        for(CpTravel travel: all){
            if(travel.getStatus() >= 1){
                travelList.add(travel);
            }
        }
        return travelList;
    }

    public List<CpTravel> getTravelByZone(String zone){
        List<CpTravel> travelList = new ArrayList<>();
        /*List<CpTravel> all = cpTravelRepository.findByZone(zone);
        for(CpTravel travel: all){

            if(travel.getStatus() == 0){

                travelList.add(travel);
            }
        }*/
        return  travelList;
    }

    public CpTravel getLastTravel(List<CpTravel> travels,CpPerson cpPerson){
        CpTravel result = new CpTravel();
        for(CpTravel travel: travels){
            LOGGER.info("TEST");
            LOGGER.info(travel.getCarId().toStringOption());
            if(travel.getCarId().getPersonId().getPersonId() == cpPerson.getPersonId()){
                result = travel;
            }
        }
        return  result;
    }
    public List<CpTravel> findActiveTravels(CpPerson person){
        List<CpTravel> result = new ArrayList<>();
        for(CpTravel travel:all()){

            if(validator.isFuture(travel.getDepartureTime()) && person.getPersonId() == travel.getCarId().getPersonId().getPersonId()){
                result.add(travel);
            }
            else{
                travel.setStatus(0);
                cpTravelRepository.save(travel);
            }
        }
        return result;
    }
    public String toStringOption(CpTravel travel){
        LOGGER.info("To string");
        LOGGER.info(travel.getTravelId());
        String result = "";
        result += ("Nombre del Conductor "+travel.getCarId().getPersonId().getFirstName()+"\n");
        result += ("Apellido del Conductor "+travel.getCarId().getPersonId().getLastName()+"\n");
        result += ("Automovil "+travel.getCarId().getBrand() + " " + travel.getCarId().getModel()+"\n");
        result += ("Placa "+travel.getCarId().getEnrollmentNumber())+"\n";
        result += ("Fecha y Hora de partida: "+travel.getDepartureTime()+"\n");
        result += ("Ruta ");
        result += travelPlaceBl.routeString(travel);
        return result;
    }
    public CpTravel getTravelByInfo(String message,CpPerson person){
        CpTravel travel = new CpTravel();
        LOGGER.info(message);
        message = message.replace("Viaje "," ");
        LOGGER.info(message);
        message = message.trim();
        LOGGER.info(message);
        int numTravel = Integer.parseInt(message);
        int ar = 0;
        for(CpTravel t:all()){
            if(person.getPersonId() == t.getCarId().getPersonId().getPersonId()){
                ar++;
                if(ar == numTravel){
                    travel = t;
                }
            }
        }
        return  travel;
    }
    public CpTravel getActiveCanceledTravel(CpPerson person){
        CpTravel travel = new CpTravel();
        for(CpTravel t:all()){
            if(t.getStatus() == 2 && person.getPersonId() == t.getCarId().getPersonId().getPersonId()){
                LOGGER.info("SELECCIONADO");
                LOGGER.info(t.getTravelId());
                travel = t;
            }
        }
        return travel;
    }

    public List<CpTravel> findActiveTravelsAll(){
        List<CpTravel> result = new ArrayList<>();
        for(CpTravel travel:all()){

            if(validator.isFuture(travel.getDepartureTime())){
                result.add(travel);
            }
            else{
                travel.setStatus(0);
                cpTravelRepository.save(travel);
            }
        }
        return result;
    }

    public List<CpTravel> selectTravels(List<CpTravel> selectTravel, CpTravelSearch search) {
        List<CpTravel> result = new ArrayList<>();
        for(CpTravel travel:selectTravel){

            if(passBy(search.getPlaceStart())){
                result.add(travel);
            }
        }
        return result;
    }

    private boolean passBy(String placeStart) {
        boolean passing=true;

        return passing;
    }
}
