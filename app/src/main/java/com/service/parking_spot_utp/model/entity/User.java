package com.service.parking_spot_utp.model.entity;

import java.io.Serializable;

public class User implements Serializable {

    private boolean valid;
    private String message;
    private String username;
    private String names;
    private String qr;
    private Vehicle vehicleDto;

    public User(boolean valid, String message, String username, String names, String qr, Vehicle vehicleDto) {
        this.valid = valid;
        this.message = message;
        this.username = username;
        this.names = names;
        this.qr = qr;
        this.vehicleDto = vehicleDto;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public Vehicle getVehicleDto() {
        return vehicleDto;
    }

    public void setVehicleDto(Vehicle vehicleDto) {
        this.vehicleDto = vehicleDto;
    }
}
