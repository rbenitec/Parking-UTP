package com.service.parking_spot_utp.presenter.service;

import com.service.parking_spot_utp.model.entity.ParkingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiParking {
    @GET("parking/assigned-place/{campus}")
    Call<ParkingResponse> getParkingAvailability(@Path("campus") String campus);
}
