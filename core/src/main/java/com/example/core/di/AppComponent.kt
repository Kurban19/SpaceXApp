package com.example.core.di

import dagger.Component
import javax.inject.Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent