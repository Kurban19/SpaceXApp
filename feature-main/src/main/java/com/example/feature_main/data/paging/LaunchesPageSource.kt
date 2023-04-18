package com.example.feature_main.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.data.toEither
import com.example.core.domain.entity.Launch
import com.example.core.domain.mapper.Either
import com.example.feature_main.data.api.Filter
import com.example.feature_main.data.api.MainService
import com.example.feature_main.data.api.Options
import com.example.feature_main.data.mapper.LaunchesResponseMapper
import java.lang.Exception

class LaunchesPageSource(
    private val mainService: MainService,
) : PagingSource<Int, Launch>() {
    override fun getRefreshKey(state: PagingState<Int, Launch>): Int {
        return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Launch> {
        return try {
            val offset: Int = params.key ?: 0
            val response = mainService.getLaunches(Filter(options = Options(offset = offset)))

            val result = response.toEither(LaunchesResponseMapper())
            if (result is Either.Failure<*>) return LoadResult.Error((result as Either.Failure).e)
            val launches = (result as Either.Success<List<Launch>>).value
            val nextKey = if (offset < (response.body()?.totalPages?.times(10) ?: 0)) offset + 10 else null
            val prevKey = if (offset == 0) null else offset - 10
            LoadResult.Page(launches, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}