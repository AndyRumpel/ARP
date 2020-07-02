package com.arsoft.arp.data.login.request

import com.arsoft.arp.data.login.response.LoginResponse
import kotlinx.coroutines.Deferred

interface LoginApiHelper {
    suspend fun login(username: String, password: String): Deferred<LoginResponse>
}