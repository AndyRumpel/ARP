package com.arsoft.arp.mvvm.login.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.arsoft.arp.R
import com.arsoft.arp.app.prefs
import com.arsoft.arp.data.DataProvider
import com.arsoft.arp.data.login.request.LoginService
import com.arsoft.arp.helpers.default
import com.arsoft.arp.helpers.set
import com.arsoft.arp.mvvm.login.states.LoginState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


class LoginViewModel {

    @Inject
    lateinit var loginApiService: LoginService
    val state = MutableLiveData<LoginState>().default(defaultValue = LoginState.DefaultState())

    private val loginRepository = DataProvider.provideLogin(apiService = loginApiService)

    fun login(username: String, password: String) {
        Log.e("apiService", "APISERVICE: ${loginApiService.hashCode()}")
        state.set(newValue = LoginState.SendingState())
        if (username == "" && password == "") {
            state.set(newValue = LoginState.ErrorState(R.string.credentials_is_empty))
        } else {
            CoroutineScope(Dispatchers.IO).async {
                try {
                    val loginResponse = loginRepository.login(username = username, password = password)
                    if (loginResponse.accessToken.isNotEmpty()) {
                        launch(Dispatchers.Main){
                            state.set(newValue = LoginState.LoginSucceededState())
                        }
                    } else {
                        launch(Dispatchers.Main) {
                            state.set(newValue = LoginState.ErrorState(message = R.string.invalid_credentials))
                            prefs.accessToken = loginResponse.accessToken
                        }
                    }
                } catch (e: Exception) {
                    launch(Dispatchers.Main) {
                        state.set(newValue = LoginState.ErrorState(e.localizedMessage!!))
                    }
                }
            }
        }

    }
}