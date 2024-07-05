package com.service.parking_spot_utp.model.dto;

import java.io.Serializable;

public class ParkingTicketDto implements Serializable {

    private String username;
    private Integer placeId;

    public ParkingTicketDto(String username, Integer placeId) {
        this.username = username;
        this.placeId = placeId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    @Override
    public String toString() {
        return "ParkingTicketDto{" +
                "username='" + username + '\'' +
                ", placeId=" + placeId +
                '}';
    }
}

