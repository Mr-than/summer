package com.example.summerassessment.services

import com.example.summerassessment.base.APP
import com.example.summerassessment.model.*
import okhttp3.RequestBody

import retrofit2.http.*
import rx.Observable

interface ApiHomeService {

    @Headers(
        "project_token:${APP.PROJECT_TOKEN}",
        "token:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNDgzOSIsImV4cCI6MTY1OTE2MjIxMH0.ATRESQWoOWrrxbqr6ERINfB0ejisdgh4Jrs77jkMKm4"
    )
    @POST("home/recommend")
    fun getRecommendList(): Observable<RecommendListBean>

    @Headers(
        "project_token:${APP.PROJECT_TOKEN}",
        "token:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNDgzOSIsImV4cCI6MTY1OTE2MjIxMH0.ATRESQWoOWrrxbqr6ERINfB0ejisdgh4Jrs77jkMKm4"
    )
    @POST("home/latest")
    fun getNewList(): Observable<RecommendListBean>

    @Headers(
        "project_token:${APP.PROJECT_TOKEN}",
        "token:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNDgzOSIsImV4cCI6MTY1OTE2MjIxMH0.ATRESQWoOWrrxbqr6ERINfB0ejisdgh4Jrs77jkMKm4"
    )
    @POST("home/text")
    fun getTextList(): Observable<RecommendListBean>

    @Headers(
        "project_token:${APP.PROJECT_TOKEN}",
        "token:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNDgzOSIsImV4cCI6MTY1OTE2MjIxMH0.ATRESQWoOWrrxbqr6ERINfB0ejisdgh4Jrs77jkMKm4"
    )
    @POST("home/pic")
    fun getPicList(): Observable<RecommendListBean>

    @Headers(
        "project_token:${APP.PROJECT_TOKEN}",
        "token:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNDgzOSIsImV4cCI6MTY1OTE2MjIxMH0.ATRESQWoOWrrxbqr6ERINfB0ejisdgh4Jrs77jkMKm4"
    )
    @POST("home/attention/list")
    fun getFollowArt(@Body requestBody: RequestBody): Observable<RecommendListBean>

    @Headers(
        "project_token:${APP.PROJECT_TOKEN}",
        "token:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNDgzOSIsImV4cCI6MTY1OTE2MjIxMH0.ATRESQWoOWrrxbqr6ERINfB0ejisdgh4Jrs77jkMKm4"
    )
    @POST("home/attention/recommend")
    fun getRecommendFollow(): Observable<RecommendFollowBean>

    @Headers(
        "project_token:${APP.PROJECT_TOKEN}",
        "token:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNDgzOSIsImV4cCI6MTY1OTE2MjIxMH0.ATRESQWoOWrrxbqr6ERINfB0ejisdgh4Jrs77jkMKm4"
    )
    @POST("jokes/comment/list")
    fun getCommentData(@Body requestBody: RequestBody):Observable<CommentData>


    @Headers(
        "project_token:${APP.PROJECT_TOKEN}",
        "token:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNDgzOSIsImV4cCI6MTY1OTE2MjIxMH0.ATRESQWoOWrrxbqr6ERINfB0ejisdgh4Jrs77jkMKm4"
    )
    @POST("jokes/like")
    fun setIsLike(@Body requestBody: RequestBody):Observable<IsLikeData>

    @Headers(
        "project_token:${APP.PROJECT_TOKEN}",
        "token:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNDgzOSIsImV4cCI6MTY1OTE2MjIxMH0.ATRESQWoOWrrxbqr6ERINfB0ejisdgh4Jrs77jkMKm4"
    )
    @POST("jokes/unlike")
    fun setIsNotLike(@Body requestBody: RequestBody):Observable<IsLikeData>

    @Headers(
        "project_token:${APP.PROJECT_TOKEN}",
        "token:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNDgzOSIsImV4cCI6MTY1OTE2MjIxMH0.ATRESQWoOWrrxbqr6ERINfB0ejisdgh4Jrs77jkMKm4"
    )
    @POST("jokes/comment")
    fun postComment(@Body requestBody: RequestBody):Observable<PostCommentRespond>

    @Headers(
        "project_token:${APP.PROJECT_TOKEN}",
        "token:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNDgzOSIsImV4cCI6MTY1OTE2MjIxMH0.ATRESQWoOWrrxbqr6ERINfB0ejisdgh4Jrs77jkMKm4"
    )
    @POST("user/attention")
    fun setUserFollow(@Body requestBody: RequestBody):Observable<AllData>

}