package com.example.freshexample.com.example.normanda_capp.Login


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Routes {

    @POST("login")
    fun login(@Body UserData:User): Call<User>

}