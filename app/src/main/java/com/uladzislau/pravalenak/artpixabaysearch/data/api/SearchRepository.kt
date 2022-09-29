package com.uladzislau.pravalenak.artpixabaysearch.data.api

import com.uladzislau.pravalenak.artpixabaysearch.data.entity.HitEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SearchRepository(
    private val api: SearchApi,
    private val dispatcher: CoroutineDispatcher
) {

    suspend fun search(query: String): Result<List<HitEntity>> =
        withContext(dispatcher) {
            runCatching { api.search(query) }
                .map { it.hits }
        }
}