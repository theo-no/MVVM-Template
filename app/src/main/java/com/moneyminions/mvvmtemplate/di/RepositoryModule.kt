package com.moneyminions.mvvmtemplate.di

import com.moneyminions.mvvmtemplate.repository.GithubRepository
import com.moneyminions.mvvmtemplate.repository.GithubRepositoryImpl
import com.moneyminions.mvvmtemplate.service.BusinessService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideGitHubRepository(businessService: BusinessService): GithubRepository{
        return GithubRepositoryImpl(businessService)
    }
}