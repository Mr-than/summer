package com.example.summerassessment.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.summerassessment.base.APP
import com.example.summerassessment.model.Data
import com.example.summerassessment.model.pagingsource.HomeRecommendPagingSource
import com.example.summerassessment.services.HomeRecommendService
import com.example.summerassessment.util.create
import kotlinx.coroutines.flow.Flow

object HomeRecommendRepository{

    private val homeRecommendService=create<HomeRecommendService>()

    suspend fun test():Int{
        return homeRecommendService.getRecommendList().code
    }

    fun getPagingData(): Flow<PagingData<Data>> {
        return Pager(
            config = PagingConfig(50),
            pagingSourceFactory = { HomeRecommendPagingSource(homeRecommendService) }
        ).flow
    }
}