package com.service.parking_spot_utp.model.entity;

public class ParkingResponse {

    private int totalSlots;
    private int availableSlots;
    private String basement;
    private String series;

    private int idSlot;

    public int getTotalSlots() {
        return totalSlots;
    }



    public int getAvailableSlots() {
        return availableSlots;
    }



    public String getBasement() {
        return basement;
    }



    public String getSeries() {
        return series;
    }



    public int getIdSlot() {
        return idSlot;
    }


}