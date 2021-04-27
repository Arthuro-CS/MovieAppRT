package com.cueva.movieapprt.application.presentation.di.login

import com.cueva.movieapprt.data.repository.LoginRepositoryImpl
import com.cueva.movieapprt.domain.abstraction.LoginRepository
import dagger.Module
import dagger.Provides

@Module
class LoginRepositoryModule {

    @Provides
    fun providesLeagueRepositoryImpl(loginRepositoryImpl: LoginRepositoryImpl) : LoginRepository{
        return loginRepositoryImpl
    }
}