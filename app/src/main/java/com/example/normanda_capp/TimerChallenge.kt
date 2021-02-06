package com.example.normanda_capp

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.freshexample.com.example.normanda_capp.EnemiesToKill
import com.example.freshexample.com.example.normanda_capp.Global
import com.example.freshexample.com.example.normanda_capp.Login.ChallengeToServer
import com.example.freshexample.com.example.normanda_capp.Login.Client
import com.example.freshexample.com.example.normanda_capp.Login.Routes
import com.example.freshexample.com.example.normanda_capp.Login.User
import com.example.freshexample.util.NotificationUtil
import com.example.freshexample.util.PrefUtil
import com.example.normanda_capp.Fragments.Challenge
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

private lateinit var  closeImage: ImageView
private lateinit var  start: ImageView
private lateinit var  stop: ImageView
private lateinit var progressText: TextView
private lateinit var progress: ProgressBar

class TimerChallenge : AppCompatActivity() {
    companion object{
        //lenthInMinutes Defines the timer Lenth AND the amount of enemies of the challenge
        var lengthInMinutes: Int = 0
        //var TIMER_LENGTH_ID = 0

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

    @SuppressLint("SimpleDateFormat")
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
            //lengthInMinutes = SimpleDateFormat.MINUTE_FIELD
        }

        stop.setOnClickListener{
            timer.cancel()
            timerState = TimerState.Stopped
            onTimerFinished()
            updateButtons()
        }

        //What Android Developers has to say? Line 107 & 108
        //	onTimeSet(TimePicker view, int hourOfDay, int minute)
        //  view	TimePicker: the view associated with this listener
        progressText.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { view, minute: Int, second: Int ->
                cal.set(Calendar.MINUTE, minute)
                //cal.set(Calendar.SECOND, second)
                //progressText.text = SimpleDateFormat.MINUTE_FIELD.toString()
                progressText.text = SimpleDateFormat("mm:00").format(cal.time)
                //PROBLEM HERE - As "Run" Says
                lengthInMinutes = minute
                ///Challenge.timeSetForChallenge = minute
                //val enemiesMustkill = 2 * lengthInMinutes
                EnemiesToKill.EnemiesToKillToAct = 2* lengthInMinutes



                //Sending to challenge
                //val timeAndEnemies = Challenge(true , lengthInMinutes, enemiesMustkill)
                //sendChallenge(timeAndEnemies)


            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND), true).show()
        }
        /////////////////////////////FINISHED THE PART OF THE PROBLEM/////////////////////////////
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
        val secondsInMinuteUntilFinished = secondsRemaining - minutesUntilFinished * 60
        val secondsStr = secondsInMinuteUntilFinished.toString()
        progressText.text = "$minutesUntilFinished:${
            if (secondsStr.length == 2) secondsStr
            else "0"+ secondsStr}"
        progress.progress = (timerLengthSeconds - secondsRemaining).toInt()
        if(timerState == TimerState.Stopped) {
            lengthInMinutes = 0
            EnemiesToKill.EnemiesToKillToAct = 0
        }
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

    fun getAmount(): Int {
        var insideAmount:Int = EnemiesToKill.EnemiesToKillToAct;
        return insideAmount;
    }

}
/*
fun sendChallenge(challenge: Challenge) {
    val retrofitClient = Client
            .getRetrofitInstance("http://10.0.2.2:3909/")
    val endpoint =retrofitClient.create(Routes::class.java)
    //val callback = endpoint.login()
    endpoint.sendChallenge(ChallengeToServer).enqueue(
              object : Callback<ChallengeToServer>{
                override fun onResponse(call: Call<ChallengeToServer>, response: Response<ChallengeToServer>) {

                    //SET USERID FOR FUTURE POSTS & GETS
                    println("AQUAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUIAQUII")
                }

                //override fun onFailure(call: Call<Challenge>, t: Throwable) {
                //    println(t.message.toString())
                //}
            }
    )
}*/
