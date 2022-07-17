package com.example.summerassessment.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.summerassessment.model.Data
import com.example.summerassessment.ui.pagingsource.HomeRecommendPagingSource
import com.example.summerassessment.services.ApiHomeService
import com.example.summerassessment.util.create
import kotlinx.coroutines.flow.Flow

object HomePageRepository{

    private val apiHomeService=create<ApiHomeService>()


    fun getPagingData():Flow<PagingData<Data>> {
        return Pager(
            config = PagingConfig(10),
            pagingSourceFactory = { HomeRecommendPagingSource(apiHomeService) }
        ).flow
    }
}