package com.example.normanda_capp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.example.freshexample.com.example.normanda_capp.Login.Client
import com.example.freshexample.com.example.normanda_capp.Login.Routes
import com.example.freshexample.com.example.normanda_capp.Login.User

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var singin: AppCompatButton

class Normanda_Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_normanda__login)

        val retrofitClient = Client
            .getRetrofitInstance("http://10.0.2.2:3909/")
        val endpoint =retrofitClient.create(Routes::class.java)
        //val callback = endpoint.login()

        //init image buttons
        singin = findViewById(R.id.sing_in)

        singin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val username = findViewById<AppCompatEditText>(R.id.text_credentials)
        val password = findViewById<AppCompatEditText>(R.id.text_password)
        val login = findViewById<AppCompatButton>(R.id.sing_in)



        login.setOnClickListener {
            println("Funciona")
            val user = User(0 ,username.text.toString(), password.text.toString())
            login(user)

        }
    }

    fun login(user: User) {
        val retrofitClient = Client
            .getRetrofitInstance("http://10.0.2.2:3909/")
        val endpoint =retrofitClient.create(Routes::class.java)
        //val callback = endpoint.login()
        endpoint.login(user).enqueue(
                object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        toast("Login Successful")
                        //println("TAG:" + response.body().userID)
                        val intent = Intent(this@Normanda_Login, MainActivity::class.java)
                        startActivity(intent)


                    }
                    override fun onFailure(call: Call<User>, t: Throwable) {
                        toast(t.message.toString())
                    }
                }
        )
    }

    private fun toast(string: String) {
        val applicationContext = this@Normanda_Login
        Toast.makeText(
                applicationContext,
                string,
                Toast.LENGTH_LONG
        ).show()
        }
    }


