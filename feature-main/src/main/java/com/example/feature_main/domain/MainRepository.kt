package com.example.feature_main.domain

import androidx.paging.PagingData
import com.example.core.domain.entity.Launch
import kotlinx.coroutines.flow.Flow

interface MainRepository  {
    fun getLaunches(): Flow<PagingData<Launch>>
}
