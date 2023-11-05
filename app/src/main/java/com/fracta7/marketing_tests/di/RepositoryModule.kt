package com.fracta7.marketing_tests.di

import com.fracta7.marketing_tests.data.repository.AppRepositoryImpl
import com.fracta7.marketing_tests.domain.repository.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun repositoryBinder(AppRepositoryImpl: AppRepositoryImpl): AppRepository

}