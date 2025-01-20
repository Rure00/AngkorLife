package com.rure.angkorlife.di

import com.rure.angkorlife.data.RetrofitClient
import com.rure.angkorlife.data.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ProvideModule {
    @Provides
    @ViewModelScoped
    fun provideRetrofitService() : RetrofitService {
        return RetrofitClient.instance.create(RetrofitService::class.java)
    }
}