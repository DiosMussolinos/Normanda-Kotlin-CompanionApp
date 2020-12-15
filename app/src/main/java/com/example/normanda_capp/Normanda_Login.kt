package com.example.normanda_capp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton

private lateinit var singin: AppCompatButton
var agePerson:Int = 39

class Normanda_Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_normanda__login)

        //init image buttons
        singin = findViewById(R.id.sing_in)

            singin.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

        }
    }
}