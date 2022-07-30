package com.example.summerassessment.services

import com.example.summerassessment.base.APP
import com.example.summerassessment.model.AllData
import com.example.summerassessment.model.MessageData
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import rx.Observable

interface MessageService {

    @Headers(
        "project_token:${APP.PROJECT_TOKEN}")
    @POST("user/message/system")
    fun getMessage(@Body requestBody: RequestBody,@Header("token") token:String): Observable<MessageData>

}