package com.example.timestamp.serviceUtils

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

class RunningApp:Application() {
    companion object {
        const val FCM_CHANNEL_ID = "FCM_CHANNEL_ID"
    }
    override fun onCreate() {
        super.onCreate()

        val channel = NotificationChannel(
            "running_channel","Running Notification",NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)



        val fcmChannel = NotificationChannel(
            FCM_CHANNEL_ID,
            "FCM_Channel", NotificationManager.IMPORTANCE_HIGH)

        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        manager.createNotificationChannel(fcmChannel)





    }
}