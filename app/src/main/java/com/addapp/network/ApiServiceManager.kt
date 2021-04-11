package com.addapp.network

import com.addapp.model.UserResponse
import retrofit2.Call
import retrofit2.HttpException
import java.lang.Exception
import java.net.HttpURLConnection

class ApiServiceManager(private val apiService: ApiService) {

    suspend fun getAllUser(): Resource<UserResponse> {
        return try {
            Resource.success(apiService.getAllUser())
        }catch (exception: Exception){
            createException(exception)
        }
    }

    private fun <T>createException(e: Throwable): Resource<T> {
        if (e is HttpException) {
            if (e.code() == HttpURLConnection.HTTP_NOT_FOUND){
                return Resource.error(null, "Data not found")
            }
        }
        return Resource.error(null, "Server Error")
    }
}