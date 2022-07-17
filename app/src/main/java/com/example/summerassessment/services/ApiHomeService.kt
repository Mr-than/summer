package com.example.summerassessment.services

import com.example.summerassessment.base.APP
import com.example.summerassessment.model.RecommendListBean
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiHomeService {

    @Headers("project_token:${APP.PROJECT_TOKEN}")
    @POST("home/recommend")
    suspend fun getRecommendList():RecommendListBean

}