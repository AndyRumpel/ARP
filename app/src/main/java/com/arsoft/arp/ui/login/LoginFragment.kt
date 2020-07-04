package com.arsoft.arp.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.arsoft.arp.R
import com.arsoft.arp.mvvm.login.states.LoginState
import com.arsoft.arp.mvvm.login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*

@AndroidEntryPoint
class LoginFragment : Fragment() {


    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers(view = view)

        login_button.setOnClickListener {
            viewModel.login(
                username = username_input.text.toString(),
                password = password_input.text.toString()
            )
        }


    }

    private fun setupObservers(view: View) {
        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when(state) {
                is LoginState.SendingState -> {
                    username_input.isEnabled = false
                    password_input.isEnabled = false
                    login_button.visibility = View.INVISIBLE
                    loading_cpv.visibility = View.VISIBLE
                }
                is LoginState.LoginSucceededState -> {
                    val bundle = bundleOf("access_token" to state.accessToken)
                    bundle.putInt("user_id", state.userId)
                    view.findNavController().navigate(R.id.action_loginFragment_to_playlistFragment, bundle)
                }
                is LoginState.ErrorState<*> -> {
                    when(state.message) {
                        is Int -> showMessage(state.message)
                        is String -> showMessage(state.message)
                    }
                    username_input.isEnabled = true
                    password_input.isEnabled = true
                    login_button.visibility = View.VISIBLE
                    loading_cpv.visibility = View.INVISIBLE
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

    private fun <T: Any> showMessage(message: T) {
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