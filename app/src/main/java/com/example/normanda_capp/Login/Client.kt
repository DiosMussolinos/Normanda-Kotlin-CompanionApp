package com.example.freshexample.com.example.normanda_capp.Login

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Client {

    companion object { // Allows to call a function of a class without having a class instance
        //Returns a Retrofit Client Instance
        fun getRetrofitInstance(path : String) : Retrofit {
            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create()) //Retrofit client can now convert to JSON
                .build()
        }
    }

}