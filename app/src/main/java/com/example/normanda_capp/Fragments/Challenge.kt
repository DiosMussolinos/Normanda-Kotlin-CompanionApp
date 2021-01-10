 package com.example.normanda_capp.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.freshexample.util.PrefUtil

import com.example.normanda_capp.R
import com.example.normanda_capp.TimerChallenge
import org.w3c.dom.Text
import java.lang.StrictMath.abs
import kotlin.properties.Delegates


class Challenge : Fragment(R.layout.fragment_challenge) {

    private lateinit var toTimer: ImageView
    private lateinit var amountText:TextView
    var timeSetForChallenge:Int = TimerChallenge.lengthInMinutes
    private val amountEnemies = abs(timeSetForChallenge*2)

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toTimer =  view.findViewById(R.id.to_challenge)
        amountText =  view.findViewById(R.id.EnAmount)
        //GO TO N1
        toTimer.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, TimerChallenge::class.java))
                finish()
            }
        }

        if(amountEnemies <= 0){
            amountText.text ="No challenge Set"
        } else {
            amountText.text = "You must kill $amountEnemies Enemies"
        }

    }

}