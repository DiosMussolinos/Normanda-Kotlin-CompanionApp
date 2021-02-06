package com.example.freshexample.com.example.normanda_capp.Login


import com.example.freshexample.Player
import com.example.normanda_capp.Fragments.Challenge
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Routes {

    @POST("login")
    fun login(@Body UserData:User): Call<User>

    @POST("playerInfo")
    fun getValues (@Body PlayerData:Player): Call<Player>

    @POST("ChallengeToTrue")
    fun sendChallenge (@Body ChallengeData:ChallengeToServer): Call<ChallengeToServer>

}