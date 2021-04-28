package com.cueva.movieapprt.domain.abstraction

interface LoginRepository {
    fun validateUserLogin(username: String, password: String) : Boolean
}