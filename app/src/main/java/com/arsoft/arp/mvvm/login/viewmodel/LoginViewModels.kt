package com.arsoft.arp.mvvm.login.viewmodel

import androidx.lifecycle.MutableLiveData
import com.arsoft.arp.R
import com.arsoft.arp.helpers.default
import com.arsoft.arp.helpers.set
import com.arsoft.arp.mvvm.login.states.LoginState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.lang.Exception

class LoginViewModels {


    val state = MutableLiveData<LoginState>().default(defaultValue = LoginState.DefaultState())

    fun login(login: String, password: String) {
        state.set(newValue = LoginState.SendingState())

        if (login == "" && password == "") {
            state.set(newValue = LoginState.ErrorState(R.string.credentials_is_empty))
        } else {
            CoroutineScope(Dispatchers.IO).async {
                try {

                } catch (e: Exception) {
                    state.set(newValue = LoginState.ErrorState(e.localizedMessage!!))
                }
            }
        }

    }
}