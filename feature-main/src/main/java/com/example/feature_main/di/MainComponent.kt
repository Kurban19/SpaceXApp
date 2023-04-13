package com.example.feature_main.di

import com.example.core.di.AppComponent
import com.example.core.di.ScreenScope
import com.example.feature_main.presentation.MainFragment
import dagger.Component

@ScreenScope
@Component(
    dependencies = [AppComponent::class],
    modules = [MainModule::class, MainViewModelModule::class]
)
interface MainComponent {

    fun inject(screen: MainFragment)

    @Component.Builder
    interface Builder {
        fun build(): MainComponent

        fun appComponent(appComponent: AppComponent): Builder
    }

    class Initializer private constructor() {
        companion object {
            fun init(
                appComponent: AppComponent,
            ): MainComponent = DaggerMainComponent
                .builder()
                .appComponent(appComponent)
                .build()
        }
    }
}