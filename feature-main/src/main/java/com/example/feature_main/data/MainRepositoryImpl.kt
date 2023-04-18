package com.example.feature_main.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.domain.entity.Launch
import com.example.feature_main.data.api.MainService
import com.example.feature_main.data.paging.LaunchesPageSource
import com.example.feature_main.domain.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val DEFAULT_PAGE_SIZE = 10

class MainRepositoryImpl @Inject constructor(
    private val mainService: MainService,
) : MainRepository {
    override fun getLaunches(): Flow<PagingData<Launch>> {
        return Pager(PagingConfig(pageSize = DEFAULT_PAGE_SIZE)) {
            LaunchesPageSource(mainService)
        }.flow
    }
}
