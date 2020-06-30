package com.arsoft.arp.data.login.models

import com.google.gson.annotations.SerializedName

data class LoginModel(
    @SerializedName(value = "access_token")
    var accessToken: String,
    @SerializedName(value = "expires_in")
    var expiresIn: Int,
    @SerializedName(value = "user_id")
    val userId: Int
)