package com.example.freshexample.com.example.normanda_capp.Login

import com.google.gson.annotations.SerializedName

data class ChallengeToServer(
        @SerializedName("Challenge")
        val challengeActivated:Boolean,

        @SerializedName("ChallengeTime")
        val challengeTime: Int,

        @SerializedName("EnemiesMustKill")
        val EnemiesMustKill:Int

)
