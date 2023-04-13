package com.example.feature_main.data.api

import android.os.Parcelable
import com.example.feature_main.data.response.LaunchResponse
import com.example.feature_main.data.response.LaunchesResponse
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MainService {
    @POST("launches/query")
    suspend fun getLaunches(
        @Body query: Filter,
    ): Response<LaunchesResponse>

    @GET("v4/launches/{id}")
    suspend fun fetchLaunchById(@Path("id") launchId: String): LaunchResponse
}
@Parcelize
data class Filter(
//    @SerializedName("query")
//    val query: com.example.feature_main.data.api.Query,
    @SerializedName("options")
    val options: Options
): Parcelable

@Parcelize
data class Query(
    @SerializedName("date_utc")
    val dateUtc: Map<String, String> = emptyMap(),
): Parcelable

@Parcelize
data class Options(
    @SerializedName("page")
    val page: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("sort")
    val sort: Map<String, String> = emptyMap(),
    @SerializedName("pagination")
    val pagination: Boolean = true
): Parcelable


