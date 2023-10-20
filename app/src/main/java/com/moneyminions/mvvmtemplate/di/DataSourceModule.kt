package com.moneyminions.mvvmtemplate.di

import android.content.Context
import com.moneyminions.mvvmtemplate.datasource.PreferenceDataSource
import com.moneyminions.mvvmtemplate.datasource.PreferenceDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideAuthDatasource(
        @ApplicationContext context: Context
    ) : PreferenceDataSource = PreferenceDataSourceImpl(context)

}