package com.example.feature_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.entity.Launch
import com.example.core.domain.mapper.Either
import com.example.feature_details.domain.DetailsInteractor
import com.example.feature_details.domain.entity.Crew
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val interactor: DetailsInteractor,
    private val launchId: String,
) : ViewModel() {

    private val _launch = MutableStateFlow<Launch?>(null)
    val launch = _launch.asStateFlow()

    private val _crew = MutableStateFlow<List<Crew>?>(null)
    val crew = _crew.asStateFlow()

    init {
        getLaunch()
        getCrew()
    }

    private fun getLaunch() {
        viewModelScope.launch {
            when (val result = interactor.fetchLaunch(launchId)) {
                is Either.Success -> {
                    _launch.value = result.value
                }
                is Either.Failure -> {
                    // TODO
                }
            }
        }
    }
    private fun getCrew() {
        viewModelScope.launch {
            when (val result = interactor.fetchCrew()) {
                is Either.Success -> {
                    _crew.value = result.value
                }
                is Either.Failure -> {
                    // TODO
                }
            }
        }
    }
}
