package com.arsoft.arp.data.login.models


data class LoginModel(
    val accessToken: String,
    val expiresIn: Int,
    val userId: Int
)