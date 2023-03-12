package com.codinginflow.imagesearch

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val DEFAULT_QUERY = "cats"
private const val CURRENT_QUERY = "current"

@HiltViewModel
class GalleryViewModel @Inject constructor(
    val repository: Repository,
    state: SavedStateHandle
) : ViewModel() {
    private val currentQuery = state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)
    val photos = currentQuery.switchMap { query ->
        repository.searchPhotos(query).cachedIn(viewModelScope)
    }

    fun searchPhotos(query: String) {
        currentQuery.value = query
    }
}