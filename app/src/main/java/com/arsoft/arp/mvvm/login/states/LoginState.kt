package com.arsoft.arp.mvvm.login.states


sealed class LoginState {

    class SendingState: LoginState()
    class LoginSucceededState: LoginState()
    class ErrorState<T>(val message: T): LoginState()
    class DefaultState: LoginState()
}