package com.example.normanda_capp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.freshexample.util.NotificationUtil
import com.example.freshexample.util.PrefUtil

class TimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        NotificationUtil.showTimerExpired(context)

        PrefUtil.setTimerState(TimerChallenge.TimerState.Stopped, context)
        PrefUtil.setAlarmSetTime(0, context)

    }
}