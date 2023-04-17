package com.example.core.domain.entity

import com.example.core.data.response.common.Cores
import com.example.core.data.response.common.Links

data class Launch(
    val id: String,
    val name: String,
    val success: Boolean,
    val details: String,
    val links: Links,
    val cores: List<Cores>,
    val fireDateUtc: String,
)
