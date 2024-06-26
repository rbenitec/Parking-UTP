package com.service.parking_spot_utp.model.entity;

public class User {

    public boolean valid;
    public String username;

    public String password;
    public String message;

    public String placa1;


    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPlaca1() {
        return placa1;
    }

    public void setPlaca1(String placa1) {
        this.placa1 = placa1;
    }

    public User(boolean valid, String username, String password, String message, String placa1) {
        this.valid = valid;
        this.username = username;
        this.password = password;
        this.message = message;
        this.placa1 = placa1;
    }
}
