package com.service.parking_spot_utp.presenter.service;


import com.service.parking_spot_utp.model.dto.Lista;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiParking {

    @GET("/parking/dashboard/park/available")
    Call<Lista> getParkingList();
}