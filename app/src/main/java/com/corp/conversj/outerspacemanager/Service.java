package com.corp.conversj.outerspacemanager;

import com.corp.conversj.outerspacemanager.Buildings.Buildings;
import com.corp.conversj.outerspacemanager.Fleet.Ship;
import com.corp.conversj.outerspacemanager.Fleet.Ships;
import com.corp.conversj.outerspacemanager.Galaxy.User;
import com.corp.conversj.outerspacemanager.Galaxy.Users;
import com.corp.conversj.outerspacemanager.Searches.Searches;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by conversj on 06/03/2017.
 */

public interface Service {
    @POST("api/v1/auth/create")
    Call<User> createUserAccount(@Body User user);
    @POST("api/v1/auth/login")
    Call<User> logUserAccount(@Body User user);
    @GET("api/v1/users/get")
    Call<User> getUser(@Header("x-access-token") String token);
    @GET("api/v1/users/0/20")
    Call<Users> getUsers(@Header("x-access-token") String token);
    @GET("api/v1/buildings/list")
    Call<Buildings> getBuildings(@Header("x-access-token") String token);
    @POST("api/v1/buildings/create/{id}")
    Call<Buildings> createBuilding(@Header("x-access-token") String token, @Path("id") String buildingId);
    @GET("api/v1/searches/list")
    Call<Searches> getSearches(@Header("x-access-token") String token);
    @POST("api/v1/searches/create/{id}")
    Call<Searches> createSearch(@Header("x-access-token") String token, @Path("id") String searchesId);
    @GET("api/v1/ships")
    Call<Ships> getShips(@Header("x-access-token") String token);
    @POST("api/v1/ships/create/{id}")
    Call<Ships> createShip(@Header("x-access-token") String token, @Path("id") String shipId, @Body Ship ship);
}
