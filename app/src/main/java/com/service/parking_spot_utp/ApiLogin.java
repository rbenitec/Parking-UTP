package com.service.parking_spot_utp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
public interface ApiLogin {

    @FormUrlEncoded
    @POST("login")
    Call<User>LOGIN_CALL(
            @Field("email") String email,
            @Field("password") String password

    );


}
