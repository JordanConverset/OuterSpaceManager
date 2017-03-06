package com.corp.conversj.outerspacemanager;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by conversj on 06/03/2017.
 */

public interface Service {
    @POST("api/v1/auth/create")
    Call<User> createUserAccount(@Body User user);
}