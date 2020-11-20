package com.example.normanda

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        //Delay de tempo
        Handler().postDelayed({

            //Going to Other Activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            //Finish Activity
            finish()

        }, 2500)
    }
}