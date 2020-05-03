package com.example.apptest1kt.Retrofit

import com.example.apptest1kt.UI.DetailFood.DetailFood
import com.example.apptest1kt.UI.HomeScreen.UserHome
import com.example.apptest1kt.UI.LoginScreeen.UserLogin
import com.example.apptest1kt.UI.RegisterScreen.UserId
import com.example.apptest1kt.UI.RegisterScreen.UserRegistor
import com.example.apptest1kt.model.FoodModel
import retrofit2.Call
import retrofit2.http.*

interface Service {
    @GET("/test7/user")
    fun getAllUser(): Call<List<UserLogin>>
    @POST("/test7/user")
    fun postUser(@Body userLogin: UserRegistor):Call<List<UserRegistor>>
    @GET("/test7/user")
    fun getUserId():Call<List<UserId>>
    @GET("/test7/user/{idUser}")
    fun getUserHome(@Path("idUser") id:Int): Call<UserHome>
    @GET("/test7/datafood")
    fun getFood():Call<List<FoodModel>>
    @GET("/test7/datafood/{idFood}")
    fun getFoodId(@Path("idFood")idFood: Int):Call<FoodModel>
}