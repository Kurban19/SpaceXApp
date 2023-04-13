package com.example.feature_main.di

import com.example.core.di.NetworkModule
import com.example.feature_main.data.MainRepositoryImpl
import com.example.feature_main.data.api.MainService
import com.example.feature_main.domain.MainRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [NetworkModule::class])
class MainModule {

    @Provides
    fun provideMainService(retrofit: Retrofit): MainService {
        return retrofit.create(MainService::class.java)
    }

    @Provides
    fun provideMainRepository(mainService: MainService): MainRepository {
        return MainRepositoryImpl(mainService)
    }
}
