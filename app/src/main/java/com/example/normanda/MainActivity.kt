package com.example.normanda

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.Toast


//__ MAIN ACTIVITY = STATS __\\
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Esconder Barra Inutil De MERDA\\
        supportActionBar?.hide()

        findViewById<Button>(R.id.map).setOnClickListener{
            Toast.makeText(this, "go to MAP", Toast.LENGTH_SHORT).show()
            //Go to Map\\
            var intent = Intent(this, Map::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.wiki).setOnClickListener{
            Toast.makeText(this, "go to WIKI", Toast.LENGTH_SHORT).show()
            //Go to Wiki\\
            var intent = Intent(this, Wiki::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.stats).setOnClickListener{
            Toast.makeText(this, "go to STATS", Toast.LENGTH_SHORT).show()
            //var intent = Intent(this, MainActivity::class.java)
            //startActivity(intent)
        }

        findViewById<Button>(R.id.challenges).setOnClickListener{
            Toast.makeText(this, "go to CHALLENGES", Toast.LENGTH_SHORT).show()
            //Go to Challenge\\
            var intent = Intent(this, Challenge::class.java)
            startActivity(intent)
        }

    }
}