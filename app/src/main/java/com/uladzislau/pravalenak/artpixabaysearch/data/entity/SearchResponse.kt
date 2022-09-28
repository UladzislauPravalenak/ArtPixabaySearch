package com.uladzislau.pravalenak.artpixabaysearch.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    @SerialName("total")
    val total: Int,
    @SerialName("totalHits")
    val totalHits: Int,
    @SerialName("hits")
    val hits: List<HitEntity>
)