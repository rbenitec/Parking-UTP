package com.service.parking_spot_utp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiLogin {
    @POST("login/authenticate")
    Call<User> LOGIN_CALL(@Body LoginRequest loginRequest);
}