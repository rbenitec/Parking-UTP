package com.service.parking_spot_utp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiRegister {

    @FormUrlEncoded
    @POST("register")
    Call<User>REGISTER_CALL(
            @Field("email") String email,
            @Field("password") String password
    );
}
