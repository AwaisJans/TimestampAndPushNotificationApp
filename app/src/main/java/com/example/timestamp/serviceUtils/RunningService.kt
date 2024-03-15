package com.example.timestamp.serviceUtils

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.timestamp.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class RunningService: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


        when(intent?.action){
            ACTION.START.toString() -> start()
            ACTION.STOP.toString() -> stopSelf()

        }

        return super.onStartCommand(intent, flags, startId)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun start(){
        val notification = NotificationCompat.Builder(this,"running_channel")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("App is Running")
            .setContentText(getCurrentDateWithDayMonth()).build()
        startForeground(1,notification)
    }


    @SuppressLint("WeekBasedYear")
    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentDateWithDayMonth(): String {
        val pattern = "EEEE,d MMMM Y"
        val formatter = DateTimeFormatter.ofPattern(pattern)
        val currentDateTime = LocalDateTime.now()
        return formatter.format(currentDateTime)
    }


    enum class ACTION{
        START,
        STOP
    }



}