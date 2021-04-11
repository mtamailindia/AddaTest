package com.addapp.model

import com.google.gson.annotations.SerializedName

data class UserResponse(@SerializedName("code") var code: Int, @SerializedName("data") var data: List<User>)
