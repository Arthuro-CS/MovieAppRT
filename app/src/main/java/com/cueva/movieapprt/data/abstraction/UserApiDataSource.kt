package com.cueva.movieapprt.data.abstraction

interface UserApiDataSource {
    fun loginUser(username: String, password: String) : Boolean
}