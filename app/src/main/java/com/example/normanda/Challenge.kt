package com.example.normanda

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

//__ MAIN ACTIVITY = STATS __\\
class Challenge : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge)

        //Esconder Barra Inutil De MERDA\\
        supportActionBar?.hide()

    }
}