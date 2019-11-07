package com.carpooling.bot.dto;

public class PersonDto {
    private Integer personId;
    private String firstName;
    private String lastName;
    private String ci_number;
    private String cellphone_num;
    public PersonDto(){

    }

    public PersonDto(Integer personId, String firstName, String lastName, String ci_number, String cellphone_num) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ci_number = ci_number;
        this.cellphone_num = cellphone_num;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
}
