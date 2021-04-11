package com.addapp.network

import com.addapp.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getAllUser(): UserResponse
}