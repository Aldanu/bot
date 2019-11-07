package com.carpooling.bot.dto;


import com.carpooling.bot.domain.CpPerson;
import com.carpooling.bot.domain.CpUser;

public class PersonDto {
    private Integer person_id;
    private String first_name;
    private String last_name;
    private String ci_number;
    private String cellphone_num;

    public PersonDto() {
    }

    public PersonDto(CpPerson cpPerson) {

        this.first_name = cpPerson.getFirstName();
        this.last_name = cpPerson.getLastName();
        this.ci_number = cpPerson.getCiNumber();
        this.cellphone_num = cpPerson.getCellphoneNumber();
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public String getCi_number() {
        return ci_number;
    }

    public void setCi_number(String ci_number) {
        this.ci_number = ci_number;
    }

    public String getCellphone_num() {
        return cellphone_num;
    }

    public void setCellphone_num(String cellphone_num) {
        this.cellphone_num = cellphone_num;
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
