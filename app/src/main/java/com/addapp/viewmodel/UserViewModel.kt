package com.addapp.viewmodel

import androidx.lifecycle.*
import com.addapp.model.User
import com.addapp.model.UserResponse
import com.addapp.network.ApiService
import com.addapp.network.ApiServiceManager
import com.addapp.network.Resource
import com.addapp.network.RetrofitConfig
import com.addapp.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class UserViewModel(): ViewModel() {

    private val repository: UserRepository = UserRepository(ApiServiceManager(RetrofitConfig.getAppService()))

    suspend fun getUserList1(): LiveData<Resource<UserResponse>> =
        liveData {
        emitSource(repository.getAllUsers())
    }

}