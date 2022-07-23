package com.example.summerassessment.services

import com.example.summerassessment.base.APP
import com.example.summerassessment.model.RecommendListBean
import com.example.summerassessment.model.UserVideoListData
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import rx.Observable

interface ApiSearchService {
    @Headers("project_token:${APP.PROJECT_TOKEN}")
    @POST("home/jokes/search")
    fun getVideoData(@Body requestBody: RequestBody): Observable<RecommendListBean>

}