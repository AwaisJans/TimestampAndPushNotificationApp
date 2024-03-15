package com.example.timestamp

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class PushNotificationScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_push_notification_screen)


        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.i("FCM_TOKEN1", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            val token = task.result


            Log.d("FCM_TOKEN1", token)
            Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
        })

    }





}