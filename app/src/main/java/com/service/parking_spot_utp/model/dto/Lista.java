package com.service.parking_spot_utp.model.dto;

import java.util.List;

public class Lista {

    private List<ParkingCampusDto> parkingCampus;

    public List<ParkingCampusDto> getParkingCampus() {
        return parkingCampus;
    }

    public void setParkingCampus(List<ParkingCampusDto> parkingCampus) {
        this.parkingCampus = parkingCampus;
    }
}
