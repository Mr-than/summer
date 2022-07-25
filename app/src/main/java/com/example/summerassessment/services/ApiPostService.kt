package com.example.summerassessment.services

import com.example.summerassessment.base.APP
import com.example.summerassessment.model.AllData
import com.example.summerassessment.model.Data
import com.example.summerassessment.model.PostTokenData
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import rx.Observable

interface ApiPostService {
    @Headers(
        "project_token:${APP.PROJECT_TOKEN}",
        "token:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNDgzOSIsImV4cCI6MTY1OTE2MjIxMH0.ATRESQWoOWrrxbqr6ERINfB0ejisdgh4Jrs77jkMKm4"
    )
    @POST("helper/qiniu/token")
    fun getToken(@Body requestBody: RequestBody): Observable<PostTokenData>

    @Headers(
        "project_token:${APP.PROJECT_TOKEN}",
        "token:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNDgzOSIsImV4cCI6MTY1OTE2MjIxMH0.ATRESQWoOWrrxbqr6ERINfB0ejisdgh4Jrs77jkMKm4"
    )
    @POST("jokes/post")
    fun postImg(@Body requestBody: RequestBody): Observable<AllData>

}