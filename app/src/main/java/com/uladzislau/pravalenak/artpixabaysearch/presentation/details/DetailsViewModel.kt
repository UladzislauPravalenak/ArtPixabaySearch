package com.uladzislau.pravalenak.artpixabaysearch.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uladzislau.pravalenak.artpixabaysearch.data.api.SearchRepository
import com.uladzislau.pravalenak.artpixabaysearch.data.api.searchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repository: SearchRepository = searchRepository
) : ViewModel() {

    val loadingStateFlow = MutableStateFlow(true)
    val detailedHitStateFlow: MutableStateFlow<DetailedHitUI> = MutableStateFlow(DetailedHitUI())

    fun find(query: String, id: Int) {
        viewModelScope.launch {
            repository
                .find(query, id)
                .onSuccess {
                    val detailed = DetailedHitUI(
                        url = it.largeImageURL,
                        userName = it.userName,
                        tags = it.tags,
                        likesCount = it.likesCount,
                        downloadsCount = it.downloadsCount,
                        commentsCount = it.commentsCount
                    )
                    detailedHitStateFlow.emit(detailed)
                    loadingStateFlow.emit(false)
                }
                .onFailure {
                    loadingStateFlow.emit(true)
                }
        }
    }
}