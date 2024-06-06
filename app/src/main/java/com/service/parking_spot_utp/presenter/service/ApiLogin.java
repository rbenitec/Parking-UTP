package com.service.parking_spot_utp.presenter.service;

import com.service.parking_spot_utp.model.dto.LoginRequest;
import com.service.parking_spot_utp.model.entity.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiLogin {
    @POST("/login/authenticate")
    Call<User> LOGIN_CALL(@Body LoginRequest loginRequest);
}