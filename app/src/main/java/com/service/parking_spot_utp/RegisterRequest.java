package com.service.parking_spot_utp;


public class RegisterRequest {

    private String username;
    private String password;
    private String plate;

    public RegisterRequest(String username, String password, String plate) {
        this.username = username;
        this.password = password;
        this.plate = plate;
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

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
}


