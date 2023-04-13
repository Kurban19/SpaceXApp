package com.example.core.presentation

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.core.di.Application
import com.example.core.presentation.vmutils.ViewModelView

abstract class BaseFragment<T : ViewModel> : Fragment(), ViewModelView<T> {

    fun getAppComponent() = (requireActivity().application as Application).appComponent
}