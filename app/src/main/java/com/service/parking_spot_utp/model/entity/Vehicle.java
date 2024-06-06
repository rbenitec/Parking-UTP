package com.service.parking_spot_utp.model.entity;

public class Vehicle {
    private String placa;
    private String modelo;
    private String type;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Vehicle(String placa, String modelo, String type) {
        this.placa = placa;
        this.modelo = modelo;
        this.type = type;
    }
}
