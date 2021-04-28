package com.cueva.movieapprt.application.presentation.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.cueva.movieapprt.R
import com.cueva.movieapprt.application.presentation.di.login.DaggerLoginComponent
import com.cueva.movieapprt.databinding.FragmentLoginBinding
import com.google.android.material.appbar.AppBarLayout
import javax.inject.Inject

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var loginViewModel: LoginViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_login, container, false
        )
        activity?.findViewById<AppBarLayout>(R.id.app_bar)?.visibility = AppBarLayout.GONE

        DaggerLoginComponent.create().inject(this)

        loginViewModel.state.observe(viewLifecycleOwner){isValid ->
            if (isValid){
                findNavController().navigate(R.id.action_loginFragment_to_moviesFragment)
            }else{
                setError()
            }
        }

        binding.setLifecycleOwner(this)
        binding.buttonLogin.setOnClickListener {
            hideError()
            validateUserLogin()
        }

        return binding.root
    }

    private fun setError(){
        binding.textError.visibility = TextView.VISIBLE
        binding.buttonLogin.visibility = Button.VISIBLE
        binding.progressBarLogin.visibility = ProgressBar.GONE
    }

    private fun hideError(){
        binding.textError.visibility = TextView.GONE
        binding.buttonLogin.visibility = Button.GONE
    }

    private fun validateUserLogin(){
        binding.progressBarLogin.visibility = ProgressBar.VISIBLE
        loginViewModel.validateUserLogin(binding.inputUsername.text.toString(),binding.inputPassword.text.toString())
    }

}