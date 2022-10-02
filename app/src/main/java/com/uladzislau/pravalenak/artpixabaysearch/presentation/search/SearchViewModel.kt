package com.uladzislau.pravalenak.artpixabaysearch.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uladzislau.pravalenak.artpixabaysearch.data.api.SearchRepository
import com.uladzislau.pravalenak.artpixabaysearch.data.api.searchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: SearchRepository = searchRepository
) : ViewModel() {

    val loadingStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val stateFlow: MutableStateFlow<List<HitUI>> = MutableStateFlow(emptyList())

    init {
        search("fruits")
    }

    fun search(query: String) {
        viewModelScope.launch {
            loadingStateFlow.emit(true)
            repository.search(query)
                .onSuccess { entities ->
                    entities
                        .map { i -> HitUI(i.id, i.tags.split(", "), i.url, i.userName) }
                        .let { stateFlow.emit(it) }
                    loadingStateFlow.emit(false)
                }
                .onFailure {
                    stateFlow.emit(emptyList())
                    loadingStateFlow.emit(false)
                }
        }
    }
}