package com.example.core.presentation.vmutils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import kotlin.reflect.KClass

/**
 * Интерфейс для удобной работы с [androidx.lifecycle.ViewModel] из view-слоя.
 */
interface ViewModelView<T : ViewModel> : ViewModelStoreOwner {

    var vmFactory: ViewModelProvider.Factory

    fun getViewModel(clazz: KClass<T>): T = ViewModelProvider(this, vmFactory)[clazz.java]

}

inline fun <reified T : ViewModel> ViewModelView<T>.viewModel(): Lazy<T> =
    lazy { getViewModel(T::class) }
