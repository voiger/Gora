package com.uialert.gora.server;

import com.uialert.gora.server.pojo.photo.Photo;
import com.uialert.gora.server.pojo.user.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonplaceholderAPI {

    @GET("/users/{id}")
    Call<User> getUserID(@Path("id") int id);

    @GET("/users")
    Call<List<User>> getAllUser();

    @GET("/albums?userId={id}")
    Call<User> getAlbumsUser(@Path("id") int userId);

    @GET("/photos")
    Call<List<Photo>> getPhotosToUserID(@Query("albumId") int albumId);

}
