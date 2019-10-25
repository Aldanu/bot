package com.carpooling.bot.dto;

public class CarDto {
    private Integer carId;
    private String marca;
    private String modelo;
    private String placa;
    private int asientos;

    public CarDto(){

    }

    public CarDto(Integer carId, String marca, String modelo, String placa, int asientos) {
        this.carId = carId;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.asientos = asientos;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAsientos() {
        return asientos;
    }

    public void setAsientos(int asientos) {
        this.asientos = asientos;
    }
}
