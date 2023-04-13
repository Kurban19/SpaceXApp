package com.example.core.data.response.common

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Links(
    @SerializedName("patch")
    val patch: Patch
) : Parcelable

@Parcelize
data class Patch(
    @SerializedName("small")
    val small: String,
    @SerializedName("large")
    val large: String,
) : Parcelable