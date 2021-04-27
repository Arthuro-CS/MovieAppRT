package com.cueva.movieapprt.data.repository

import com.cueva.movieapprt.data.abstraction.UserApiDataSource
import com.cueva.movieapprt.domain.abstraction.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val userApiDataSource: UserApiDataSource) : LoginRepository{
    override fun validateUserLogin(username: String, password: String): Boolean {
        return userApiDataSource.loginUser(username,password)
    }
}