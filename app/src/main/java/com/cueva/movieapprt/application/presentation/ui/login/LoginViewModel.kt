package com.cueva.movieapprt.application.presentation.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cueva.movieapprt.domain.usecase.ValidateUserLoginUseCase
import javax.inject.Inject

class LoginViewModel @Inject constructor(val validateUserLoginUseCase: ValidateUserLoginUseCase): ViewModel(){

    private val _state = MutableLiveData<Boolean>()

    val state: LiveData<Boolean>
        get() = _state

    fun validateUserLogin(username: String, password: String){
        val result = validateUserLoginUseCase.validateUserLogin(username,password)
        _state.value= result
    }
}