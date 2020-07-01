package com.arsoft.arp.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.arsoft.arp.R
import com.arsoft.arp.data.login.request.LoginService
import com.arsoft.arp.mvvm.login.states.LoginState
import com.arsoft.arp.mvvm.login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {


    private val viewModel = LoginViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login_button.setOnClickListener {
            viewModel.login(
                username = username_input.text.toString(),
                password = password_input.text.toString()
            )
        }

        viewModel.state.observe(viewLifecycleOwner, Observer<LoginState> { state ->
            when(state) {
                is LoginState.SendingState -> {
                    username_input.isEnabled = false
                    password_input.isEnabled = false
                    login_button.visibility = View.INVISIBLE
                    loading_cpv.visibility = View.VISIBLE
                }
                is LoginState.LoginSucceededState -> {
                    Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_playlistFragment)
                }
                is LoginState.ErrorState<*> -> {
                    when(state.message) {
                        is Int -> showMessage(state.message)
                        is String -> showMessage(state.message)
                    }
                }
                is LoginState.DefaultState -> {
                    username_input.isEnabled = true
                    password_input.isEnabled = true
                    login_button.visibility = View.VISIBLE
                    loading_cpv.visibility = View.INVISIBLE
                }
            }
        })
    }

    fun <T: Any> showMessage(message: T) {
        when(message) {
            is Int -> {
                Toast.makeText(requireContext(), resources.getText(message), Toast.LENGTH_SHORT).show()
            }
            is String -> {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(requireContext(), message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }




}