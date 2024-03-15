package com.example.timestamp

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

class FirstScreen : AppCompatActivity() {

    private lateinit var btnTimestampScreen: Button
    private lateinit var btnPushNotification: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)

        btnTimestampScreen = findViewById(R.id.idTimestamp)
        btnPushNotification = findViewById(R.id.idPushNotify)

        askNotificationPermission()

    }


    private fun permissionGrantedClickListeners() {
        btnTimestampScreen.setOnClickListener {
            startActivity(Intent(this@FirstScreen, TimestampScreen::class.java))
        }

        btnPushNotification.setOnClickListener {
            startActivity(Intent(this@FirstScreen, PushNotificationScreen::class.java))
        }
    }


    // Declare the launcher at the top of your Activity/Fragment:
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            permissionGrantedClickListeners()
        } else {
            btnTimestampScreen.setOnClickListener {
                Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show()
            }

            btnPushNotification.setOnClickListener {
                Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                permissionGrantedClickListeners()
            } else {
                requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }


}














