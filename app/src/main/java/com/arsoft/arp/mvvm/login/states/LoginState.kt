package com.arsoft.arp.mvvm.login.states


sealed class LoginState {

    class SendingState: LoginState()
    class LoginSucceededState(val accessToken: String, val userId: Int): LoginState()
    class ErrorState<T>(val message: T): LoginState()
    class DefaultState: LoginState()
}