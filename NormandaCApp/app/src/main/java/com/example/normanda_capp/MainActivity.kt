package com.example.normanda_capp

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity() {

    private lateinit var  mViewPager: ViewPager
    private lateinit var  stats:ImageButton
    private lateinit var  map:ImageButton
    private lateinit var  wiki:ImageButton
    private lateinit var  exercise:ImageButton
    private lateinit var  mPagerAdapter: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init view
        mViewPager = findViewById(R.id.mViewPager)

        //init image buttons
        stats = findViewById(R.id.stats)
        map = findViewById(R.id.map)
        wiki = findViewById(R.id.wiki)
        exercise = findViewById(R.id.exercise)

        mPagerAdapter = PagerViewAdaptor(supportFragmentManager)
        mViewPager.adapter = mPagerAdapter
        mViewPager.offscreenPageLimit = 5

        //add page change listener

        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                changingTabs(position)
            }
        })

        //default tab
        mViewPager.currentItem = 0
        stats.setImageResource(R.drawable.ic_baseline_sports_white)

    }

    private fun changingTabs(position: Int) {
        if(position == 0){
            stats.setImageResource(R.drawable.ic_baseline_sports_white)
            map.setImageResource(R.drawable.ic_baseline_map)
            wiki.setImageResource(R.drawable.ic_baseline_library_books)
            exercise.setImageResource(R.drawable.ic_baseline_fitness)

            //map.setImageResource(R.drawable.ic_baseline_map_white)
            //wiki.setImageResource(R.drawable.ic_baseline_library_books_white)
            //exercise.setImageResource(R.drawable.ic_baseline_fitness_white)
        }
        if(position == 1){
            stats.setImageResource(R.drawable.ic_baseline_sports_kabaddi_24)
            map.setImageResource(R.drawable.ic_baseline_map_white)
            wiki.setImageResource(R.drawable.ic_baseline_library_books)
            exercise.setImageResource(R.drawable.ic_baseline_fitness)
        }
        if(position == 2){
            stats.setImageResource(R.drawable.ic_baseline_sports_kabaddi_24)
            map.setImageResource(R.drawable.ic_baseline_map)
            wiki.setImageResource(R.drawable.ic_baseline_library_books_white)
            exercise.setImageResource(R.drawable.ic_baseline_fitness)
        }
        if(position == 1){
            stats.setImageResource(R.drawable.ic_baseline_sports_kabaddi_24)
            map.setImageResource(R.drawable.ic_baseline_map)
            wiki.setImageResource(R.drawable.ic_baseline_library_books)
            exercise.setImageResource(R.drawable.ic_baseline_fitness_white)
        }
    }
}