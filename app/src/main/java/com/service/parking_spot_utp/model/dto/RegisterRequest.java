package com.service.parking_spot_utp.model.dto;

import com.service.parking_spot_utp.model.entity.Vehicle;

public class RegisterRequest {

    private String username;
    private String password;
    private Vehicle vehicle;

    public RegisterRequest(String username, String password, Vehicle vehicle) {
        this.username = username;
        this.password = password;
        this.vehicle = vehicle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}

