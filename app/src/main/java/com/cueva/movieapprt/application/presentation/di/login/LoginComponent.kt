package com.cueva.movieapprt.application.presentation.di.login

import com.cueva.movieapprt.application.presentation.ui.login.LoginFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [LoginRepositoryModule::class, UserDataSourceModule::class])
interface LoginComponent {
    fun inject(loginFragment: LoginFragment)
}