package com.example.normanda

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        findViewById<Button>(R.id.map).setOnClickListener{

            Toast.makeText(this, "go to MAP", Toast.LENGTH_SHORT).show()

        }

        findViewById<Button>(R.id.wiki).setOnClickListener{

            Toast.makeText(this, "go to WIKI", Toast.LENGTH_SHORT).show()

        }

        findViewById<Button>(R.id.stats).setOnClickListener{

            Toast.makeText(this, "go to STATS", Toast.LENGTH_SHORT).show()

        }

        findViewById<Button>(R.id.challenges).setOnClickListener{

            Toast.makeText(this, "go to CHALLENGES", Toast.LENGTH_SHORT).show()

        }

    }
}