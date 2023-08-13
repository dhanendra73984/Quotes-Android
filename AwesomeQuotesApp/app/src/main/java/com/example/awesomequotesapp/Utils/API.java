package com.example.awesomequotesapp.Utils;

import com.example.awesomequotesapp.Entity.FavQuote;
import com.example.awesomequotesapp.Entity.Quote;
import com.example.awesomequotesapp.Entity.User;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API {

    public static final String BASE_URL = "http://192.168.44.206:4004";


    @POST ("/user/login")
    Call<JsonObject> userLogin(@Body User user);

    @POST("/user/register")
    Call<JsonObject> userRegister(@Body User user);


    @GET("/quotes/all")
    Call<JsonObject> getAllQuote();

    @GET("/quotes/byuserid/{id}")
    Call<JsonObject> getQuotesByUserID(@Path("id") int id);

    @POST("/quotes/addquote/{key}")
    Call<JsonObject> addQuotes(@Path("key") int key, @Body Quote quote);

    @GET("/user/getuserbyid/{idd}")
    Call<JsonObject> getuserByuserId(@Path("idd")int id);

    @POST("/quotes/favquotes")
    Call<JsonObject> addFavQuotes(@Body FavQuote favQuote);

    @DELETE("/quotes/delbyquote/{id}")
    Call<JsonObject> delquotes(@Path("id") int id);

    @GET("/quotes/favquotes/{id}")
    Call<JsonObject> getFavQuotes(@Path("id") int id);

    @PUT("/quotes/editquote")
    Call<JsonObject> editQuote(@Body Quote quote);

    @DELETE("/quotes/delquote/{id}")
    Call<JsonObject> delq(@Path("id") int id);

    @PUT("/user/edit/{id}")
    Call<JsonObject> editUser(@Path("id") int id,@Body User user);
}
