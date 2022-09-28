package com.uladzislau.pravalenak.artpixabaysearch.data.api

import com.uladzislau.pravalenak.artpixabaysearch.BuildConfig
import com.uladzislau.pravalenak.artpixabaysearch.data.entity.SearchResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val END_POINT_GET = "https://pixabay.com/api/"

class SearchApi(private val client: HttpClient) {
    suspend fun search(query: String): SearchResponse =
        client.get(END_POINT_GET) {
            parameter("key", BuildConfig.API_KEY)
            parameter("q", query)
        }
}