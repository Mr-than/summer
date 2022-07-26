package com.example.summerassessment.services

import com.example.summerassessment.base.APP
import com.example.summerassessment.model.*
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import rx.Observable

interface ApiUserService {

    @Headers("project_token:${APP.PROJECT_TOKEN}")
    @POST("jokes/video/like/list")
    fun getVideoData(@Body requestBody: RequestBody): Observable<UserVideoListData>

    @Headers("project_token:${APP.PROJECT_TOKEN}")
    @POST("jokes/text_pic_jokes/like/list")
    fun getTextImgData(@Body requestBody: RequestBody):Observable<RecommendListBean>

    @Headers("project_token:${APP.PROJECT_TOKEN}")
    @POST("jokes/text_pic_jokes/list")
    fun getUserOwnTextImgData(@Body requestBody: RequestBody):Observable<RecommendListBean>

    @Headers("project_token:${APP.PROJECT_TOKEN}")
    @POST("jokes/video/list")
    fun getUserOwnVideoData(@Body requestBody: RequestBody): Observable<UserVideoListData>

    @Headers("project_token:${APP.PROJECT_TOKEN}")
    @POST("user/info/target")
    fun getUserMessage(@Body requestBody: RequestBody): Observable<UserData>

    @Headers("project_token:${APP.PROJECT_TOKEN}")
    @POST("user/comment/list")
    fun gerComment(@Body requestBody: RequestBody,@Header("token") token:String):Observable<CurrentCommentData>


}