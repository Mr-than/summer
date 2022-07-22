package com.example.summerassessment.services

import com.example.summerassessment.base.APP
import com.example.summerassessment.model.BrushVideoData
import retrofit2.http.Headers
import retrofit2.http.POST
import rx.Observable

interface ApiPlayService {
    @Headers("project_token:${APP.PROJECT_TOKEN}")
    @POST("douyin/list")
    fun getVideoData(): Observable<BrushVideoData>

}