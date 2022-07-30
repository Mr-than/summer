package com.example.summerassessment.services

import com.example.summerassessment.base.APP
import com.example.summerassessment.model.AllData
import com.example.summerassessment.model.Data
import com.example.summerassessment.model.PostTokenData
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import rx.Observable

interface PostService {
    @Headers(
        "project_token:${APP.PROJECT_TOKEN}")
    @POST("helper/qiniu/token")
    fun getToken(@Body requestBody: RequestBody,@Header("token") token:String): Observable<PostTokenData>

    @Headers(
        "project_token:${APP.PROJECT_TOKEN}")
    @POST("jokes/post")
    fun postImg(@Body requestBody: RequestBody,@Header("token") token:String): Observable<AllData>

}