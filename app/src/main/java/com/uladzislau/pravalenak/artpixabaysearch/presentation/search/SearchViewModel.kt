package com.uladzislau.pravalenak.artpixabaysearch.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uladzislau.pravalenak.artpixabaysearch.data.api.SearchRepository
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: SearchRepository = SearchRepository()) : ViewModel() {

    fun search(query: String) {
        viewModelScope.launch {
            repository.search(query)
        }
    }
}