package com.codinginflow.imagesearch2

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(val service: UnsplashService) {
    fun searchPhotos(query: String) = Pager(
        config = PagingConfig(10, 100, false),
        pagingSourceFactory = { UnsplashSource(service, query) }
    ).liveData
}