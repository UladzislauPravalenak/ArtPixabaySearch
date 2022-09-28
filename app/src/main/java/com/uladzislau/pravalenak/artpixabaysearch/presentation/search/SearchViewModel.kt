package com.uladzislau.pravalenak.artpixabaysearch.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uladzislau.pravalenak.artpixabaysearch.data.api.SearchApi
import com.uladzislau.pravalenak.artpixabaysearch.data.api.SearchRepository
import com.uladzislau.pravalenak.artpixabaysearch.data.api.ktorHttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: SearchRepository =
        SearchRepository(
            SearchApi(ktorHttpClient),
            Dispatchers.IO
        )
) : ViewModel() {

    val stateFlow: MutableStateFlow<List<HitUI>> = MutableStateFlow(emptyList())

    init {
        search("fruits apple")
    }

    fun search(query: String) {
        viewModelScope.launch {
            repository.search(query)
                .onSuccess { entities ->
                    entities
                        .map { i -> HitUI(i.id, i.tags.split(", "), i.url, i.userName) }
                        .let { stateFlow.emit(it) }
                }
                .onFailure {
                    stateFlow.emit(emptyList())
                }
        }
    }
}