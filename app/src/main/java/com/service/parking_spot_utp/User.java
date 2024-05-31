package com.service.parking_spot_utp;

public class User {
    public String email;
    public String password;
    public String token;
    public String placa1;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPlaca1() {
        return placa1;
    }

    public void setPlaca1(String placa1) {
        this.placa1 = placa1;
    }


    public User(String email, String password, String token, String placa1) {
        this.email = email;
        this.password = password;
        this.token = token;
        this.placa1 = placa1;

    }
}
