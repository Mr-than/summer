package com.example.summerassessment.services

import com.example.summerassessment.base.APP
import com.example.summerassessment.model.AllData
import com.example.summerassessment.model.AttentionUserListData
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import rx.Observable

interface ApiFollowService {

    @Headers(
        "project_token:${APP.PROJECT_TOKEN}")
    @POST("user/attention/list")
    fun userFollowList(@Body requestBody: RequestBody,@Header("token") token:String): Observable<AttentionUserListData>

    @Headers(
        "project_token:${APP.PROJECT_TOKEN}")
    @POST("user/fans/list")
    fun userFanList(@Body requestBody: RequestBody,@Header("token") token:String): Observable<AttentionUserListData>

    @Headers(
        "project_token:${APP.PROJECT_TOKEN}")
    @POST("user/attention")
    fun setUserFollow(@Body requestBody: RequestBody,@Header("token") token:String): Observable<AllData>



}