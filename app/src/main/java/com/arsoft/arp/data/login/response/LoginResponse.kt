package com.arsoft.arp.data.login.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName(value = "access_token")
    val accessToken: String,
    @SerializedName(value = "expires_in")
    val expiresIn: Int,
    @SerializedName(value = "user_id")
    val userId: Int
)