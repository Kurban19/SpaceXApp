package com.example.spacexapp

import android.app.Application
import com.example.core.di.AppComponent
import com.example.core.di.DaggerAppComponent

class App : Application(), com.example.core.di.Application {

    override val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .build()
    }
}
