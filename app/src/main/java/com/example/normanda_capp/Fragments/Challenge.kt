package com.example.normanda_capp.Fragments

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import com.example.freshexample.util.PrefUtil
import com.example.normanda_capp.R
//import kotlinx.android.synthetic.main.fragment_your_fragment_name.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Challenge.newInstance] factory method to
 * create an instance of this fragment.
 */
class Challenge : Fragment() {

    private lateinit var  start: ImageView
    private lateinit var  stop: ImageView
    private lateinit var  challengeCountdown: ProgressBar


    enum class TimerState{
        Stopped, Running
    }

    private lateinit var timer: CountDownTimer
    private var timerLengthSeconds = 0L
    private var timerState = TimerState.Stopped

    private var secondsRemaining = 0L


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        //init image buttons
        //ERROR HERE
        start = findViewById(R.id.playbutton)
        //ERROR HERE
        stop = findViewById(R.id.stopbutton)

        start.setOnClickListener {
            startTimer()
            timerState = TimerState.Stopped
            updateButtons()
        }

        stop.setOnClickListener {
            timer.cancel()
            onTimerFinished()
        }
    }

    override  fun onResume(){
        super.onResume()

        initTimer()
        //TODO: remove background timer, hide notification
    }

    override fun onPause(){
        super.onPause()

        if(timerState == TimerState.Running){
            timer.cancel()
            //TODO: Background Timer
        }
        else if(timerState == TimerState.Stopped){
            //TODO: Show notification
        }

        //ERROR HERE
        PrefUtil.setPreviousTimerLengthSeconds(timerLengthSeconds, this)
        //ERROR HERE
        PrefUtil.setSecondsRemaining(secondsRemaining, this)
        //ERROR HERE
        PrefUtil.setTimerState(timerState, this)
    }

    private fun initTimer(){
        //ERROR HERE
        timerState = PrefUtil.getTimerState(this)

        if(timerState == TimerState.Stopped) {
            setNewTimerLength()
        }
        else {
            setPreviousTimerLength()
        }

        secondsRemaining = if(timerState == TimerState.Running) {
            //ERROR HERE
            PrefUtil.getSecondsRemaining(this)
        }else {
            timerLengthSeconds
        }

        //TODO: Change secondsRemaining According to where the background timer stopped

        //resume where we left off
        if(timerState == TimerState.Running) {
            startTimer()
        }
        updateButtons()
        updateCountdownUI()
    }

    private fun onTimerFinished(){
        timerState = TimerState.Stopped

        setNewTimerLength()

        //id counter
        challengeCountdown.progress = 0

        //ERROR HERE
        PrefUtil.setSecondsRemaining(timerLengthSeconds, this)
        secondsRemaining = timerLengthSeconds

        updateButtons()
        updateCountdownUI()
    }

    private fun startTimer(){
        timerState = TimerState.Running

        timer = object: CountDownTimer(secondsRemaining * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000
                updateCountdownUI()
            }

            override fun onFinish() {
                TODO("Not yet implemented")
            }
        }.start()
    }

    private fun setNewTimerLength(){
        //ERROR HERE
        val lengthInMinutes = PrefUtil.getTimerLength(this)
        timerLengthSeconds = (lengthInMinutes * 60L)
        challengeCountdown.max = timerLengthSeconds.toInt()
    }

    private fun setPreviousTimerLength(){
        //ERROR HERE
        timerLengthSeconds = PrefUtil.getPreviousTimerLengthSeconds(this)
        challengeCountdown.max = timerLengthSeconds.toInt()
    }

    private fun updateCountdownUI(){
        val minutesUntilFinished = secondsRemaining / 60
        val secondsInMinutesUntilFinished = secondsRemaining - minutesUntilFinished * 60
        val secondsStr = secondsInMinutesUntilFinished.toString()

        //ERROR HERE
        textCountdown.text = "$minutesUntilFinished:${if(secondsStr.length == 2) secondsStr
        else "0$secondsStr"}"
        challengeCountdown.progress = (timerLengthSeconds - secondsRemaining).toInt()

    }

    private fun updateButtons(){
        when (timerState){
            TimerState.Running ->{
                start.isEnabled = false
                stop.isEnabled = true
            }

            TimerState.Stopped ->{
                start.isEnabled = true
                stop.isEnabled = false
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_challenge, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Challenge.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Challenge().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}