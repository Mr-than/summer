package com.example.summerassessment.services

import com.example.summerassessment.base.APP
import com.example.summerassessment.model.BrushVideoData
import com.example.summerassessment.model.CurrentUserData
import com.example.summerassessment.model.UserData
import retrofit2.http.Headers
import retrofit2.http.POST
import rx.Observable

interface ApiMyService {

    @Headers(
        "project_token:${APP.PROJECT_TOKEN}",
        "token:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNDgzOSIsImV4cCI6MTY1OTE2MjIxMH0.ATRESQWoOWrrxbqr6ERINfB0ejisdgh4Jrs77jkMKm4"
    )
    @POST("user/info")
    fun getUser(): Observable<CurrentUserData>
}