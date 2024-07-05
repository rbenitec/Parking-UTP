package com.service.parking_spot_utp.presenter.service;

import com.service.parking_spot_utp.model.dto.ParkingTicketDto;
import com.service.parking_spot_utp.model.entity.ticketGenerated;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiTicket {
    @   POST("/ticket/created")
    Call<ticketGenerated> createParkingTicket(@Body ParkingTicketDto parkingTicketDto);
}
