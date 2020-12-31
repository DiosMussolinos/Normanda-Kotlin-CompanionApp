package com.example.normanda_capp

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import com.example.freshexample.util.NotificationUtil
import com.example.freshexample.util.PrefUtil
import java.text.SimpleDateFormat
import java.util.*

private lateinit var  closeImage: ImageView
private lateinit var  start: ImageView
private lateinit var  stop: ImageView
private lateinit var progressText: TextView
private lateinit var progress: ProgressBar


class TimerChallenge : AppCompatActivity() {

    companion object{
        @RequiresApi(Build.VERSION_CODES.KITKAT)
        fun setAlarm(context: Context, nowSeconds: Long, secondsRemaining:Long):Long{
            val wakeUpTime = (nowSeconds + secondsRemaining) * 1000
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            val intent = Intent(context, TimerExpiredReceiver::class.java)
            val pedingIntent = PendingIntent.getBroadcast(context, 0, intent,0)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, wakeUpTime, pedingIntent)
            PrefUtil.setAlarmSetTime(nowSeconds, context)

            return wakeUpTime
        }

        fun removeAlarm(context: Context){
            val intent = Intent(context, TimerExpiredReceiver::class.java)
            val pedingIntent = PendingIntent.getBroadcast(context, 0, intent,0)
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            alarmManager.cancel(pedingIntent)

            PrefUtil.setAlarmSetTime(0, context)
        }

        val nowSeconds:Long
            get() = Calendar.getInstance().timeInMillis/1000
    }

    enum class TimerState{
        Stopped, Running
    }

    private lateinit var timer: CountDownTimer
    private var timerLengthSeconds = 0L
    private var timerState = TimerState.Stopped

    private var secondsRemaining = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer_challenge)

        //Close
        closeImage = findViewById(R.id.backToActivity)
        //TimerComands
        start = findViewById(R.id.start)
        stop = findViewById(R.id.stop)
        progress = findViewById(R.id.progress_countdown)
        progressText = findViewById(R.id.text_countdown)
        //Close
        closeImage.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //TimerComands
        start.setOnClickListener{
            startTimer()
            timerState = TimerState.Running
            updateButtons()
        }

        stop.setOnClickListener{
            timer.cancel()
            timerState = TimerState.Stopped
            onTimerFinished()
            updateButtons()
        }

        progressText.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener {TimePicker, minute: Int, second: Int ->
                cal.set(Calendar.MINUTE, minute)
                cal.set(Calendar.SECOND, second)
                progressText.text = SimpleDateFormat("mm:ss").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND), true).show()
        }

    }

    override fun onResume(){
        super.onResume()

        initTimer()

        NotificationUtil.hideTimerNotification(context = this)

        removeAlarm(this)

    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onPause(){
        super.onPause()

        if(timerState == TimerState.Running)
        {
            timer.cancel()
            val wakeUpTime = setAlarm(this, nowSeconds, secondsRemaining)
            NotificationUtil.showTimerRunning(this, wakeUpTime)
            //Todo: Start background timer
        }

        PrefUtil.setPreviousTimerLengthSeconds(timerLengthSeconds, this)
        PrefUtil.setSecondsRemaining(secondsRemaining, this)
        PrefUtil.setTimerState(timerState, this)
    }

    //EU ODEIO A MINHA VIDA

    private fun initTimer(){
        timerState = PrefUtil.getTimerState(this)

        if(timerState == TimerState.Stopped)
            setNewTimerLenght()
        else
            setPreviousTimerLenght()

        secondsRemaining = if(timerState == TimerState.Running)
            PrefUtil.getSecondsRemaining(this)
        else
            timerLengthSeconds

        //CHANGE SECONDS REMAINING
        val alarmSetTime = PrefUtil.getAlarmSetTime(this)
        if(alarmSetTime>0)
            secondsRemaining -= nowSeconds - alarmSetTime

        if(alarmSetTime<=0)
            onTimerFinished()
        else if(timerState == TimerState.Running)
            startTimer()

        updateButtons()
        updateCountdownUI()
    }

    private fun onTimerFinished(){
        timerState = TimerState.Stopped

        setNewTimerLenght()

        //progressText.progress = 0
        progress.progress = 0
        PrefUtil.setSecondsRemaining(timerLengthSeconds, this)
        secondsRemaining = timerLengthSeconds

        updateButtons()
        updateCountdownUI()
    }

    private fun startTimer(){
        timerState = TimerState.Running
        timer = object:CountDownTimer(secondsRemaining*1000, 1000) {
            override fun onFinish() = onTimerFinished()

            override fun onTick(millisUntilFinished: Long){
                secondsRemaining = millisUntilFinished/1000
                updateCountdownUI()
            }
        }.start()
    }

    private fun setNewTimerLenght(){
        val lengthInMinutes = PrefUtil.getTimerLenght(this)
        //PrefUtil.getTimerLenght(this)
        timerLengthSeconds = (lengthInMinutes * 60L)
        progress.max = timerLengthSeconds.toInt()
    }

    private fun setPreviousTimerLenght(){
        timerLengthSeconds = PrefUtil.getPreviousTimerLengthSeconds(this)
        progress.max = timerLengthSeconds.toInt()
    }

    private fun updateCountdownUI(){
        val minutesUntilFinished = secondsRemaining/60
        val secondsInMinuteUntilFinished = secondsRemaining - minutesUntilFinished*60
        val secondsStr = secondsInMinuteUntilFinished.toString()
        progressText.text = "$minutesUntilFinished:${
            if (secondsStr.length == 2) secondsStr
        else "0"+ secondsStr}"
        progress.progress = (timerLengthSeconds - secondsRemaining).toInt()
    }

    private fun updateButtons(){
        when(timerState){
            TimerState.Running -> {
                start.isEnabled = true
                stop.isEnabled = true
            }
            TimerState.Stopped -> {
                start.isEnabled = true
                start.isEnabled = true
            }
        }
    }











}