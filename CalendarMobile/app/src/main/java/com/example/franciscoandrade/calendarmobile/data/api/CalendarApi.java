package com.example.franciscoandrade.calendarmobile.data.api;

import com.example.franciscoandrade.calendarmobile.data.model.CalendarResponse;
import com.example.franciscoandrade.calendarmobile.data.model.PostRemainder;
import com.example.franciscoandrade.calendarmobile.data.model.Remainder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CalendarApi {
    @GET("/days")
    Call<List<CalendarResponse>> getDaysList();

    @GET("/days/{id}/remainders")
    Call<List<Remainder>> getRemainders(@Path("id") String id);

    @POST("/days/{id}/remainders")
    Call<PostRemainder>postRemainder(@Path("id") String id, @Body PostRemainder task);

    @DELETE("/remainders/{id}")
    Call<PostRemainder> deleteRemainder(@Path("id") String id);
}
