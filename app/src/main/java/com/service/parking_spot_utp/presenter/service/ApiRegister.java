package com.service.parking_spot_utp.presenter.service;

import com.service.parking_spot_utp.model.dto.RegisterRequest;
import com.service.parking_spot_utp.model.entity.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiRegister {
    @POST("/login/account-created")
    Call<User> REGISTER_CALL(@Body RegisterRequest RegisterRequest);
}
