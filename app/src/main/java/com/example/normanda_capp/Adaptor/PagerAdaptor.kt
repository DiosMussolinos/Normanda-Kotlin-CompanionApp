package com.example.normanda_capp.Adaptor

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.normanda_capp.Fragments.Challenge
import com.example.normanda_capp.Fragments.Map
import com.example.normanda_capp.Fragments.StatsFragment
import com.example.normanda_capp.Fragments.Wiki

internal class PagerAdaptor(fm:FragmentManager?):
        FragmentPagerAdapter(fm!!){
    override fun getItem(position: Int): Fragment {

        return when(position){
            0 -> {
                StatsFragment()
            }
            1 -> {
                Map()
            }
            2 -> {
                Wiki()
            }
            3 -> {
                Challenge()
            }
            else -> StatsFragment()
        }

    }

    override fun getCount(): Int {
        return 5
    }

}