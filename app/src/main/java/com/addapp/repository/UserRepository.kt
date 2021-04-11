package com.addapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.addapp.model.User
import com.addapp.model.UserResponse
import com.addapp.network.ApiServiceManager
import com.addapp.network.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(private val apiServiceManager: ApiServiceManager) {

    suspend fun getAllUsers(): LiveData<Resource<UserResponse>> {
        val liveData = MutableLiveData<Resource<UserResponse>>()
        liveData.postValue(apiServiceManager.getAllUser())
        return liveData
    }
}