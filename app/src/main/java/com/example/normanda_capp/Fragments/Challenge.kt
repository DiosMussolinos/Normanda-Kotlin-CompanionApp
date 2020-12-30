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
import com.example.normanda_capp.R
import com.example.normanda_capp.TimerChallenge
import org.w3c.dom.Text
import kotlin.properties.Delegates


class Challenge : Fragment(R.layout.fragment_challenge) {

    private lateinit var toTimer: Button
    private lateinit var AmountText:TextView
    private var AmountEnemies by Delegates.notNull<Int>()

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
        AmountText =  view.findViewById(R.id.EnAmount)

        //GO TO N1
        toTimer.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, TimerChallenge::class.java))
                finish()
            }
        }

        AmountEnemies = 0

        if(AmountEnemies <= 0){
            AmountText.text ="No challenge Set"
        } else {
            AmountText.text = "You must kill $AmountEnemies Enemies"
        }

    }

}