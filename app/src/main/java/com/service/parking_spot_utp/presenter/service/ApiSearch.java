package com.service.parking_spot_utp.presenter.service;

import com.service.parking_spot_utp.model.entity.ticketResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiSearch {
    @GET("/parking/dashboard/park/available")
    Call<List<ticketResponse>> getAvailableTickets();
}
