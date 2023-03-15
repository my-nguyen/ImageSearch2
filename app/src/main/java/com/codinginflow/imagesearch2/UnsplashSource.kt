package com.codinginflow.imagesearch2

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

private const val START_PAGE = 1

class UnsplashSource(val service: UnsplashService, val query: String): PagingSource<Int, UnsplashData.Result>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashData.Result> {
        val position = params.key ?: START_PAGE
        return try {
            val response = service.searchPhotos(query, position, params.loadSize)
            LoadResult.Page(
                response.results,
                if (position == START_PAGE) null else position-1,
                if (response.results.isEmpty()) null else position+1)
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UnsplashData.Result>): Int? {
        return null
    }
}