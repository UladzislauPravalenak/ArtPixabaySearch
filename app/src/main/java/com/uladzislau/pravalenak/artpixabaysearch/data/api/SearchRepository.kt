package com.uladzislau.pravalenak.artpixabaysearch.data.api

import com.uladzislau.pravalenak.artpixabaysearch.data.entity.HitEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

val searchRepository: SearchRepository = SearchRepository(SearchApi(ktorHttpClient), Dispatchers.IO)

class SearchRepository(
    private val api: SearchApi,
    private val dispatcher: CoroutineDispatcher
) {
    private val entitiesMap = hashMapOf<String, List<HitEntity>>()

    suspend fun search(query: String): Result<List<HitEntity>> =
        withContext(dispatcher) {

            when (entitiesMap.containsKey(query)) {
                false ->
                    runCatching { api.search(query) }
                        .map { it.hits }
                        .onSuccess { entitiesMap[query] = it }
                true -> Result.success(entitiesMap[query] ?: emptyList())
            }
        }

    suspend fun find(query: String, id: Int): Result<HitEntity> =
        withContext(dispatcher) {
            when (entitiesMap.containsKey(query)) {
                false -> search(query).map { it.first { it.id == id } }
                true -> Result.success(requireNotNull(entitiesMap[query]).first { it.id == id })
            }
        }
}