package com.carpooling.bot.dto;

public class RiderDto {
    private Integer rider_id;
    private String first_name;
    private String last_name;

    public RiderDto() {
    }

    public RiderDto(Integer rider_id, String first_name, String last_name) {
        this.rider_id = rider_id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Integer getRider_id() {
        return rider_id;
    }

    public void setRider_id(Integer rider_id) {
        this.rider_id = rider_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
