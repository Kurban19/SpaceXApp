package com.example.core.presentation.vmutils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Provider

/**
 * Возвращает фабирку вью моделей для переданного провайдера
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified M : ViewModel> Provider<M>.getViewModelFactory(): ViewModelProvider.Factory =
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass == M::class.java) {
                return get() as T
            } else {
                error("Unknown view model type: ${modelClass.simpleName}")
            }
        }
    }
