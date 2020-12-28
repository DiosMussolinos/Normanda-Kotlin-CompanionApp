package com.example.normanda_capp.Fragments

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.freshexample.util.PrefUtil
import com.example.normanda_capp.R
//import kotlinx.android.synthetic.main.fragment_your_fragment_name.view.*

class Challenge : Fragment(R.layout.fragment_challenge) {

    private lateinit var  start: ImageView
    private lateinit var  stop: ImageView
    private lateinit var  challengeCountdown: ProgressBar
    private lateinit var textCountdown: TextView


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

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init image buttons
        start = view.findViewById(R.id.playbutton)
        stop = view.findViewById(R.id.stopbutton)

        start.setOnClickListener {
            startTimer()
            timerState = TimerState.Running
            updateButtons()
        }

        stop.setOnClickListener {
            timer.cancel()
            timerState = TimerState.Stopped
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
        PrefUtil.setPreviousTimerLengthSeconds(timerLengthSeconds,this.requireActivity())
        //ERROR HERE
        PrefUtil.setSecondsRemaining(secondsRemaining, this.requireActivity())
        //ERROR HERE
        PrefUtil.setTimerState(timerState, this.requireActivity())
    }

    private fun initTimer(){
        //ERROR HERE
        timerState = PrefUtil.getTimerState(this.requireActivity())

        if(timerState == TimerState.Stopped) {
            setNewTimerLength()
        }
        else {
            setPreviousTimerLength()
        }

        secondsRemaining = if(timerState == TimerState.Running) {
            //ERROR HERE
            PrefUtil.getSecondsRemaining(this.requireActivity())
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
        PrefUtil.setSecondsRemaining(timerLengthSeconds, this.requireActivity())
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
        val lengthInMinutes = PrefUtil.getTimerLength(this.requireActivity())
        timerLengthSeconds = (lengthInMinutes * 60L)
        //   challengeCountdown.max = timerLengthSeconds.toInt()
    }

    private fun setPreviousTimerLength(){
        //ERROR HERE
        timerLengthSeconds = PrefUtil.getPreviousTimerLengthSeconds(this.requireActivity())
        challengeCountdown.max = timerLengthSeconds.toInt()
    }

    private fun updateCountdownUI(){
        val minutesUntilFinished = secondsRemaining / 60
        val secondsInMinutesUntilFinished = secondsRemaining - minutesUntilFinished * 60
        val secondsStr = secondsInMinutesUntilFinished.toString()

        //ERROR HERE
        //    textCountdown.text = "$minutesUntilFinished:${if(secondsStr.length == 2) secondsStr
        //  else "0$secondsStr"}"
        // challengeCountdown.progress = (timerLengthSeconds - secondsRemaining).toInt()

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

}