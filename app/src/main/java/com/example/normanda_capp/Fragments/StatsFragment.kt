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
import com.example.normanda_capp.R
import javax.xml.xpath.XPath


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class StatsFragment : Fragment() {

    private lateinit var  health: TextView
    private lateinit var  gold: TextView
    private lateinit var  experience: TextView
    private lateinit var  level: TextView

    private val playerhealth:Int = 100
    private val Money:Int = 400
    private val XP:Int = 5
    private val LevelShow:Int = 2


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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats, container, false)




    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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