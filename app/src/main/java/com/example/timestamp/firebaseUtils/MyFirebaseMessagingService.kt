package com.example.timestamp.firebaseUtils

import android.app.NotificationManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.timestamp.R
import com.example.timestamp.serviceUtils.RunningApp
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.i("FCM_TOKEN", token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        message.notification?.let {
            val title = it.title
            val body = it.body

            val notification = NotificationCompat.Builder(this, RunningApp.FCM_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(body)
                .build()

            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(1002, notification)
        }

        Log.i("DATA_MESSAGE", message.data.toString())
    }
}