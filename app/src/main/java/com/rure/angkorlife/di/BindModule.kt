package com.rure.angkorlife.di

import com.rure.angkorlife.data.RetrofitRepositoryImpl
import com.rure.angkorlife.domain.repository.RetrofitRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class BindModule {
    @Binds
    @ViewModelScoped
    abstract fun provideRetrofitRepository(impl: RetrofitRepositoryImpl): RetrofitRepository
}