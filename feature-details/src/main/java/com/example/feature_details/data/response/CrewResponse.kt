package com.example.feature_details.data.response

import android.os.Parcelable
import com.example.core.data.response.NetworkResponse
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CrewResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("agency")
    val agency: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("status")
    val status: String,
) : NetworkResponse, Parcelable

@Parcelize
class CrewsResponse : ArrayList<CrewResponse>(), NetworkResponse, Parcelable
