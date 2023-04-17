package com.example.feature_details.di

import com.example.core.di.AppComponent
import com.example.core.di.ScreenScope
import com.example.feature_details.presentation.DetailsFragment
import dagger.BindsInstance
import dagger.Component

@ScreenScope
@Component(
    dependencies = [AppComponent::class],
    modules = [DetailsModule::class, MainViewModelModule::class]
)
interface DetailsComponent {

    fun inject(screen: DetailsFragment)

    @Component.Builder
    interface Builder {
        fun build(): DetailsComponent

        fun appComponent(appComponent: AppComponent): Builder

        @BindsInstance
        fun launchId(launchId: String): Builder
    }

    class Initializer private constructor() {
        companion object {
            fun init(
                appComponent: AppComponent,
                launchId: String,
            ): DetailsComponent = DaggerDetailsComponent
                .builder()
                .appComponent(appComponent)
                .launchId(launchId)
                .build()
        }
    }
}