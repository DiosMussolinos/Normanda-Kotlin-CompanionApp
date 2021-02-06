package com.example.normanda_capp.Fragments

import Map.Neighbourhood.Neighbourhood1
import Map.Neighbourhood.Neighbourhood2
import Map.Neighbourhood.Neighbourhood3
import Map.Neighbourhood.Neighbourhood4
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.freshexample.Items
import com.example.freshexample.Player
import com.example.freshexample.com.example.normanda_capp.Global
import com.example.freshexample.com.example.normanda_capp.Login.Client
import com.example.freshexample.com.example.normanda_capp.Login.Routes
import com.example.freshexample.com.example.normanda_capp.Login.User
import com.example.freshexample.util.PrefUtil
import com.example.normanda_capp.MainActivity
import com.example.normanda_capp.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.xml.xpath.XPath


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private var playerhealth:Int = 0
private var Money:Int = 0
private var XP:Int = 0
private var LevelShow:Int = 0

class StatsFragment : Fragment() {

    private lateinit var  health: TextView
    private lateinit var  gold: TextView
    private lateinit var  experience: TextView
    private lateinit var  level: TextView



    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_stats, container, false)

    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println(Global.id)

        println("AQUAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUII")
        println("HP: " + playerhealth + ", Gold:" + Money + ", exp: "+ XP +", Level: " + LevelShow)

        val player = Player( Global.id, 101,20,30,40)
        getValues(player)

        //playerhealth = hp

       //image buttons
        health = view.findViewById(R.id.Health)
        gold = view.findViewById(R.id.Gold)
        experience = view.findViewById(R.id.exp)
        level = view.findViewById(R.id.Level)


        health.text = "Health =  $playerhealth"
        gold.text = "Gold = $Money"
        experience.text = "XP = $XP"
        level.text = "Level = $LevelShow"
    }

}

fun getValues(player: Player) {
    val retrofitClient = Client
            .getRetrofitInstance("http://10.0.2.2:3909/")
    val endpoint =retrofitClient.create(Routes::class.java)
    //val callback = endpoint.login()
    endpoint.getValues(player).enqueue(
            object : Callback<Player> {
                override fun onResponse(call: Call<Player>, response: Response<Player>) {
                    //SET USERID FOR FUTURE POSTS & GETS
                    //Health
                    var hpFromServer:Int = response.body()!!.health
                    playerhealth = hpFromServer;
                    //Gold
                    var goldFromServer:Int = response.body()!!.gold
                    Money = goldFromServer;
                    //exp
                    var expFromServer:Int = response.body()!!.Exp
                    XP = expFromServer;
                    //level
                    var levelFromServer:Int = response.body()!!.Level
                    LevelShow = levelFromServer;

                    //println("HP: $playerhealth, Gold:$Money, exp: $XP, Level: $LevelShow")

                }

                override fun onFailure(call: Call<Player>, t: Throwable) {
                    println(t.message.toString())
                }
            }
    )
}
