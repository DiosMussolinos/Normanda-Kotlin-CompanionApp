package com.example.normanda_capp.Fragments

import Map.Neighbourhood.Neighbourhood1
import Map.Neighbourhood.Neighbourhood2
import Map.Neighbourhood.Neighbourhood3
import Map.Neighbourhood.Neighbourhood4
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.normanda_capp.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



class Map : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var n1Button: ImageView
    private lateinit var n2Button: ImageView
    private lateinit var n3Button: ImageView
    private lateinit var n4Button: ImageView



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
        return inflater.inflate(R.layout.fragment_map, container, false)
}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init image buttons
        n1Button =  view.findViewById(R.id.mapN1)
        n2Button =  view.findViewById(R.id.mapN2)
        n3Button =  view.findViewById(R.id.mapN3)
        n4Button =  view.findViewById(R.id.mapN4)

        //GO TO N1
        n1Button.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, Neighbourhood1::class.java))
                finish()
            }
        }

        //GO TO N2
        n2Button.setOnClickListener {
            requireActivity().run {
                 startActivity(Intent(this, Neighbourhood2::class.java))
                 finish()
            }
        }

        //GO TO N3
        n3Button.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, Neighbourhood3::class.java))
                finish()
            }
        }

        //GO TO N4
        n4Button.setOnClickListener{
            requireActivity().run {
                startActivity(Intent(this, Neighbourhood4::class.java))
                finish()
            }
        }
    }
}
