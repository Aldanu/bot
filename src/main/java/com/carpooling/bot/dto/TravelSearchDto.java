package com.carpooling.bot.dto;

import com.carpooling.bot.domain.CpTravelSearch;

public class TravelSearchDto {
    private Integer travelSearch_id;
    private Integer person_id;
    private String place_start;
    private String place_finish;
    private String departure_time;

    public TravelSearchDto(){

    }

    public TravelSearchDto(CpTravelSearch cpTravelSearch) {
        this.travelSearch_id = cpTravelSearch.getTravelSearchId();
        this.person_id = cpTravelSearch.getIdPerson();
        this.place_start = cpTravelSearch.getPlaceStart();
        this.place_finish = cpTravelSearch.getPlaceFinish();
        this.departure_time = cpTravelSearch.getDepartureTime();
    }

    public Integer getTravelSearch_id() {
        return travelSearch_id;
    }

    public void setTravelSearch_id(Integer travelSearch_id) {
        this.travelSearch_id = travelSearch_id;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public String getPlace_start() {
        return place_start;
    }

    public void setPlace_start(String place_start) {
        this.place_start = place_start;
    }

    public String getPlace_finish() {
        return place_finish;
    }

    public void setPlace_finish(String place_finish) {
        this.place_finish = place_finish;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }
}
