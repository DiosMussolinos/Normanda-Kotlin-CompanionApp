package com.example.normanda_capp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.freshexample.AppConstants
import com.example.freshexample.util.NotificationUtil
import com.example.freshexample.util.PrefUtil

class TimerNotificationActionReceiver : BroadcastReceiver() {


    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onReceive(context: Context, intent: Intent) {
        when(intent.action){
            AppConstants.ACTION_STOP -> {
                TimerChallenge.removeAlarm(context)
                PrefUtil.setTimerState(TimerChallenge.TimerState.Stopped, context)
                NotificationUtil.hideTimerNotification(context)
            }

            AppConstants.ACTION_START -> {
                val minutesRemaining = PrefUtil.getTimerLenght(context)
                val secondsRemaining = minutesRemaining * 60L
                val wakeUpTime = TimerChallenge.setAlarm(context, TimerChallenge.nowSeconds, secondsRemaining)

                PrefUtil.setTimerState(TimerChallenge.TimerState.Running, context)
                PrefUtil.setSecondsRemaining(secondsRemaining, context)
                NotificationUtil.showTimerRunning(context, wakeUpTime)

            }
        }
    }
}