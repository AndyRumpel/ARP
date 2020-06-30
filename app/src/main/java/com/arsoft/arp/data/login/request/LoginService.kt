package com.arsoft.arp.data.login.request

import com.arsoft.arp.data.login.models.LoginModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginService {

    @GET
    fun login(
        @Query(value = "grant_type")grantType: String,
        @Query(value = "client_id")clientId: String,
        @Query(value = "client_secret")clientSecret: String,
        @Query(value = "username")username: String,
        @Query(value = "password")password: String,
        @Query(value = "scope")scope: String
    ): Deferred<LoginModel>

}