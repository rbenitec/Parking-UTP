package com.service.parking_spot_utp;

public class User {

    public boolean valid;
    public String username;
    public String message;


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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User(boolean valid, String username, String message) {
        this.valid = valid;
        this.username = username;
        this.message = message;
    }
}
