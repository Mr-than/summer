package com.example.summerassessment.services

import com.example.summerassessment.base.APP
import com.example.summerassessment.model.AllData
import com.example.summerassessment.model.LoginData
import com.example.summerassessment.model.MessageData
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import rx.Observable

interface ApiLoginService {

    @Headers("project_token:${APP.PROJECT_TOKEN}")
    @POST("user/login/get_code")
    fun getCode(@Body requestBody: RequestBody): Observable<AllData>

    @Headers("project_token:${APP.PROJECT_TOKEN}")
    @POST("user/login/code")
    fun logIn(@Body requestBody: RequestBody): Observable<LoginData>

}