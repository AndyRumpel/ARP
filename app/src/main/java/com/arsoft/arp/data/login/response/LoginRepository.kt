package com.arsoft.arp.data.login.response


import com.arsoft.arp.data.login.request.LoginService
import javax.inject.Inject

class LoginRepository(private val apiService: LoginService) {

    val GRANT_TYPE = "password"
    val CLIENT_ID = "2274003"
    val CLIENT_SECRET = "hHbZxrka2uZ6jB1inYsH"
    val SCOPE = "audio"


    suspend fun login(username: String, password: String): LoginResponse {
        return apiService.login(
            grantType = GRANT_TYPE,
            clientId = CLIENT_ID,
            clientSecret = CLIENT_SECRET,
            username = username,
            password = password,
            scope = SCOPE
        ).await()
    }
}