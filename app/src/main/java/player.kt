package com.example.freshexample

import com.google.gson.annotations.SerializedName

data class Player(
        @SerializedName("user_id")
        val userID: Int,

        @SerializedName("user_hp")
        val health:Int,

        @SerializedName("user_gold")
        val gold:Int,

        @SerializedName("user_exp")
        val Exp:Int,

        @SerializedName("user_level")
        val Level:Int)

