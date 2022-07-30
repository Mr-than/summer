package com.example.summerassessment.services

import com.example.summerassessment.base.APP
import com.example.summerassessment.model.BrushVideoData
import com.example.summerassessment.model.CurrentUserData
import com.example.summerassessment.model.UserData
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import rx.Observable

interface MyService {

    @Headers(
        "project_token:${APP.PROJECT_TOKEN}")
    @POST("user/info")
    fun getUser(@Header("token") token:String): Observable<CurrentUserData>
}