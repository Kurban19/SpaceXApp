package com.example.core.data.response.common

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cores(
    @SerializedName("flight")
    val flight: Int,
) : Parcelable
