package com.cueva.movieapprt.application.presentation.di.login

import com.cueva.movieapprt.application.framework.network.UserDataSource
import com.cueva.movieapprt.data.abstraction.UserApiDataSource
import dagger.Module
import dagger.Provides

@Module
class UserDataSourceModule {

    @Provides
    fun providesDataSource(userDataSource: UserDataSource) : UserApiDataSource{
        return userDataSource
    }
}