package com.service.parking_spot_utp.model.dto;

import java.io.Serializable;

public class ParkingTicketDto implements Serializable {

    private String username;
    private Integer accountId;
    private Integer placeId;

    public ParkingTicketDto(String username, Integer accountId, Integer placeId) {
        this.username = username;
        this.accountId = accountId;
        this.placeId = placeId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }
}

