package com.example.feature_main.domain.entity

import com.example.core.data.response.common.Cores
import com.example.core.data.response.common.Links

data class Launch(
    val id: String,
    val name: String,
    val success: Boolean,
    val links: Links,
    val cores: List<Cores>,
    val fireDateUtc: String,
)
