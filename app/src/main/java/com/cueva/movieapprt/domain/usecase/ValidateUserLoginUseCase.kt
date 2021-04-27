package com.cueva.movieapprt.domain.usecase

import com.cueva.movieapprt.domain.abstraction.LoginRepository
import javax.inject.Inject

class ValidateUserLoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    fun validateUserLogin(username: String, password: String) : Boolean{
        return loginRepository.validateUserLogin(username,password)
    }
}