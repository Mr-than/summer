package com.example.summerassessment.ui.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.summerassessment.model.Data
import com.example.summerassessment.services.ApiHomeService

class HomeRecommendPagingSource(private val apiHomeService: ApiHomeService):PagingSource<Int,Data>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val page = params.key ?: 1
            //val pageSize = params.loadSize

            val repoResponse = apiHomeService.getRecommendList()

            val repoItems = repoResponse.data
            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (repoItems.isNotEmpty()) page + 1 else null
            LoadResult.Page(repoItems, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? =null
}