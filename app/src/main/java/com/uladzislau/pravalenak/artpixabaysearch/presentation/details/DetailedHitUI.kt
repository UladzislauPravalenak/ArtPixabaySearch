package com.uladzislau.pravalenak.artpixabaysearch.presentation.details

data class DetailedHitUI(
    val url: String = "",
    val userName: String = "",
    val tags: String = "",
    val likesCount: Int = 0,
    val downloadsCount: Int = 0,
    val commentsCount: Int = 0,
)