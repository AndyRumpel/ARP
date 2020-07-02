package com.arsoft.arp.data.login.repository


import com.arsoft.arp.data.login.request.LoginApiHelper
import javax.inject.Inject

class LoginRepository @Inject constructor(private val loginApiHelper: LoginApiHelper) {

    suspend fun login(username: String, password: String) = loginApiHelper.login(
        username = username,
        password = password
    )

}