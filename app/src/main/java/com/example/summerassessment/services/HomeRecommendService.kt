package com.example.summerassessment.services

import com.example.summerassessment.base.APP
import com.example.summerassessment.model.RecommendListBean
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface HomeRecommendService {

    @Headers("project_token:6CF1090AB4834C63A5D5AAC91343F858")
    @POST("home/recommend")
    suspend fun getRecommendList():RecommendListBean

}