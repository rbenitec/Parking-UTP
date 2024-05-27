package com.service.parking_spot_utp;

public class User {
    public String email;
    public String passworwd;

    public String token;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassworwd() {
        return passworwd;
    }

    public void setPassworwd(String passworwd) {
        this.passworwd = passworwd;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User(String email, String passworwd, String token) {
        this.email = email;
        this.passworwd = passworwd;
        this.token = token;
    }
}
