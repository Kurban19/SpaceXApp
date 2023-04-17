package com.example.feature_details.di

import com.example.core.di.NetworkModule
import com.example.feature_details.data.DetailsRepositoryImpl
import com.example.feature_details.data.api.DetailsService
import com.example.feature_details.domain.DetailsRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [NetworkModule::class])
class DetailsModule {

    @Provides
    fun provideDetailsService(retrofit: Retrofit): DetailsService {
        return retrofit.create(DetailsService::class.java)
    }

    @Provides
    fun provideDetailsRepository(mainService: DetailsService): DetailsRepository {
        return DetailsRepositoryImpl(mainService)
    }
}
