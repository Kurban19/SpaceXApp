package com.example.feature_details.di

import androidx.lifecycle.ViewModelProvider
import com.example.core.di.ScreenScope
import com.example.core.presentation.vmutils.getViewModelFactory
import com.example.feature_details.presentation.DetailsViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class MainViewModelModule {

    @Provides
    @ScreenScope
    fun providesViewModelFactory(viewModelProvider: Provider<DetailsViewModel>):
            ViewModelProvider.Factory = viewModelProvider.getViewModelFactory()
}
