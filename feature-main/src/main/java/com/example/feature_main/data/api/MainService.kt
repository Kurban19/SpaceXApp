package com.example.feature_main.data.api

import android.os.Parcelable
import com.example.core.data.response.common.LaunchesResponse
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MainService {
    @POST("launches/query")
    suspend fun getLaunches(
        @Body query: Filter = Filter(),
    ): Response<LaunchesResponse>
}

@Parcelize
data class Filter(
    @SerializedName("query")
    val query: Query = Query(),
    @SerializedName("options")
    val options: Options = Options()
) : Parcelable

@Parcelize
data class Query(
    @SerializedName("date_utc")
    val dateUtc: Map<String, String> = mapOf("\$gt" to "2021-01-24T22:30:00.000Z"),
) : Parcelable

@Parcelize
data class Options(
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("limit")
    val limit: Int = 10,
    @SerializedName("offset")
    val offset: Int = 0,
    @SerializedName("sort")
    val sort: Map<String, String> = mapOf("date_utc" to "asc"),
    @SerializedName("pagination")
    val pagination: Boolean = true
) : Parcelable


