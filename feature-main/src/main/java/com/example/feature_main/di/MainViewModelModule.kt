package com.example.feature_main.di

import androidx.lifecycle.ViewModelProvider
import com.example.core.di.ScreenScope
import com.example.core.presentation.vmutils.getViewModelFactory
import com.example.feature_main.presentation.MainViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class MainViewModelModule {

    @Provides
    @ScreenScope
    fun providesViewModelFactory(viewModelProvider: Provider<MainViewModel>):
            ViewModelProvider.Factory = viewModelProvider.getViewModelFactory()
}
