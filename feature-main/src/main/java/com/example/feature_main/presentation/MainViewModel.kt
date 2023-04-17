package com.example.feature_main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.mapper.Either
import com.example.feature_main.domain.MainInteractor
import com.example.core.domain.entity.Launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val interactor: MainInteractor
) : ViewModel() {

    private val _launches = MutableStateFlow<List<Launch>?>(null)
    val launches = _launches.asStateFlow()

    init {
        getLaunches()
    }

    private fun getLaunches() {
        viewModelScope.launch {
            when (val result = interactor.fetchLaunches()) {
                is Either.Success -> {
                    _launches.value = result.value
                }
                is Either.Failure -> {
                    // TODO
                }
            }
        }
    }
}
