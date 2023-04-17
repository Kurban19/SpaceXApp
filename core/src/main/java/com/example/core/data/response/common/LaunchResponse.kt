package com.example.core.data.response.common

import android.os.Parcelable
import com.example.core.data.response.NetworkResponse
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LaunchesResponse(
    @SerializedName("docs")
    val docs: List<LaunchResponse>,
    @SerializedName("totalDocs")
    val totalDocs: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("totalPages")
    val totalPages: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("pagingCounter")
    val pagingCounter: Int,
    @SerializedName("hasPrevPage")
    val hasPrevPage: Boolean,
    @SerializedName("hasNextPage")
    val hasNextPage: Boolean,
    @SerializedName("prevPage")
    val prevPage: Int,
    @SerializedName("nextPage")
    val nextPage: Int
) : NetworkResponse, Parcelable

@Parcelize
data class LaunchResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("links")
    val links: Links,
    @SerializedName("cores")
    val cores: List<Cores>,
    @SerializedName("static_fire_date_utc")
    val fireDateUtc: String?,
    @SerializedName("details")
    val details: String?,
) : NetworkResponse, Parcelable
