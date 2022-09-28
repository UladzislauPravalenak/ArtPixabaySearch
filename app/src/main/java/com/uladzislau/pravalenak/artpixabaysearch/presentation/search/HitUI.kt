package com.uladzislau.pravalenak.artpixabaysearch.presentation.search

data class HitUI(
    val id: Int,
    val tags: List<String>,
    val url: String,
    val userName: String
)