package com.example.timestamp

import android.Manifest.permission
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.timestamp.firebaseUtils.FirebaseService
import com.example.timestamp.firebaseUtils.PostData
import com.example.timestamp.firebaseUtils.RetrofitInstance
import com.example.timestamp.serviceUtils.RunningService
import retrofit2.Callback
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class TimestampScreen : AppCompatActivity() {

    var sendDatabtn: Button? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timestamp)


        ActivityCompat.requestPermissions(this, arrayOf(permission.POST_NOTIFICATIONS),0)

        val serviceIntent = Intent(this@TimestampScreen,RunningService::class.java)
        serviceIntent.action = RunningService.ACTION.START.toString()
        startService(serviceIntent)

    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDestroy() {
        super.onDestroy()
        postTimestamp()


        Handler(Looper.getMainLooper()).postDelayed({
            val serviceIntent = Intent(this@TimestampScreen,RunningService::class.java)
            serviceIntent.action = RunningService.ACTION.STOP.toString()
            startService(serviceIntent)
        }, 1000)


    }


    private val firebaseService: FirebaseService = RetrofitInstance.retrofit.create(FirebaseService::class.java)
    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentTimestampString(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")
        val currentDateTime = LocalDateTime.now()
        return formatter.format(currentDateTime)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun postTimestamp(){
        val postData = PostData(getCurrentTimestampString())
        firebaseService.postData(postData).enqueue(object : Callback<Void> {
            override fun onResponse(
                call: retrofit2.Call<Void>,
                response: retrofit2.Response<Void>
            ) {
                if (response.isSuccessful) {
                    // Handle successful response
                    Toast.makeText(this@TimestampScreen, "data posted successfully.", Toast.LENGTH_SHORT).show()
                } else {
                    // Handle error
                    Toast.makeText(this@TimestampScreen, "failed to save data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: retrofit2.Call<Void>, t: Throwable) {
                // Handle network failure
                Toast.makeText(this@TimestampScreen, "Network Failure", Toast.LENGTH_SHORT).show()

            }
        })
    }



}