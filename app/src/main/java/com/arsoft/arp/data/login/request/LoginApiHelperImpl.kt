package com.arsoft.arp.data.login.request

import com.arsoft.arp.data.login.response.LoginResponse
import com.arsoft.arp.helpers.CLIENT_ID
import com.arsoft.arp.helpers.CLIENT_SECRET
import com.arsoft.arp.helpers.GRANT_TYPE
import com.arsoft.arp.helpers.SCOPE
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class LoginApiHelperImpl @Inject constructor(private val loginApiService: LoginApiService): LoginApiHelper  {
    override suspend fun login(username: String, password: String): Deferred<LoginResponse> = loginApiService.login(
        grantType = GRANT_TYPE,
        clientId = CLIENT_ID,
        clientSecret = CLIENT_SECRET,
        username = username,
        password = password,
        scope = SCOPE
    )
}