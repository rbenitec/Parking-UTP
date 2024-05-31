package com.service.parking_spot_utp;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiRegister {

    @POST("Register/authenticate")
    Call<User> REGISTER_CALL(@Body RegisterRequest RegisterRequest);

}

