package com.uladzislau.pravalenak.artpixabaysearch.data.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchRepository {

    suspend fun search(query: String) {
        withContext(Dispatchers.IO) {
            query.replace(" ", "+")
        }
    }
}