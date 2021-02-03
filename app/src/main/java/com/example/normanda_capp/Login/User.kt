package com.example.freshexample.com.example.normanda_capp.Login

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("user_id")
    val userID:Int,

    @SerializedName("user_name")
    val name:String,

    @SerializedName("user_password")
    val password:String
)
