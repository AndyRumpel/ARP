package com.arsoft.arp.mvvm.login.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arsoft.arp.R
import com.arsoft.arp.data.login.repository.LoginRepository
import com.arsoft.arp.helpers.Prefs
import com.arsoft.arp.helpers.default
import com.arsoft.arp.helpers.set
import com.arsoft.arp.mvvm.login.states.LoginState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception


class LoginViewModel @ViewModelInject constructor(
    private val loginRepository: LoginRepository,
    private val prefs: Prefs
): ViewModel() {

    val state = MutableLiveData<LoginState>().default(defaultValue = LoginState.DefaultState())


    fun login(username: String, password: String) {
        state.set(newValue = LoginState.SendingState())
        if (username == "" && password == "") {
            state.set(newValue = LoginState.ErrorState(R.string.credentials_is_empty))
        } else {
            CoroutineScope(Dispatchers.IO).async {
                try {
                    val loginResponse = loginRepository.login(username = username, password = password).await()
                    if (loginResponse.accessToken.isNotEmpty()) {
                        launch(Dispatchers.Main){
                            state.set(newValue = LoginState.LoginSucceededState(
                                    accessToken = loginResponse.accessToken,
                                    userId = loginResponse.userId
                            ))
                            prefs.accessToken = loginResponse.accessToken
                            prefs.userId = loginResponse.userId
                        }
                    } else {
                        launch(Dispatchers.Main) {
                            state.set(newValue = LoginState.ErrorState(message = R.string.invalid_credentials))

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