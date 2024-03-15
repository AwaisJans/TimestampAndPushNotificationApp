package com.example.timestamp.firebaseUtils

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PUT

interface FirebaseService {
    @PUT("TimestampInfo/itemTimestamp.json") // Replace with your actual endpoint
    fun postData(@Body postData: PostData): Call<Void>
}