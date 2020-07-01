package com.arsoft.arp.data.login.models

import com.google.gson.annotations.SerializedName

data class LoginModel(
    val accessToken: String,
    val expiresIn: Int,
    val userId: Int
)