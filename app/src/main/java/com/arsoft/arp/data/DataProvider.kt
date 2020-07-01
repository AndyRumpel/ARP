package com.arsoft.arp.data

import com.arsoft.arp.data.login.request.LoginService
import com.arsoft.arp.data.login.response.LoginRepository
import javax.inject.Inject

class DataProvider {
    companion object {
        fun provideLogin(apiService: LoginService): LoginRepository {
            return LoginRepository(
                apiService = apiService
            )
        }
    }
}