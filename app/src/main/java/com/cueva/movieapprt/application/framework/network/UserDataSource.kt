package com.cueva.movieapprt.application.framework.network

import com.cueva.movieapprt.data.abstraction.UserApiDataSource
import javax.inject.Inject

class UserDataSource @Inject constructor(): UserApiDataSource {
    override fun loginUser(username: String, password: String): Boolean {
        return username == "Admin" && password=="Password*123"
    }
}