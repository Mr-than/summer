package com.example.summerassessment.services

import com.example.summerassessment.base.APP
import com.example.summerassessment.model.*
import okhttp3.RequestBody

import retrofit2.http.*
import rx.Observable

interface ApiHomeService {

    @Headers(
        "project_token:${APP.PROJECT_TOKEN}")
    @POST("home/recommend")
    fun getRecommendList(@Header("token") token:String): Observable<RecommendListBean>

    @Headers(
        "project_token:${APP.PROJECT_TOKEN}")
    @POST("home/latest")
    fun getNewList(@Header("token") token:String): Observable<RecommendListBean>

    @Headers(
        "project_token:${APP.PROJECT_TOKEN}")
    @POST("home/text")
    fun getTextList(@Header("token") token:String): Observable<RecommendListBean>

    @Headers(
        "project_token:${APP.PROJECT_TOKEN}")
    @POST("home/pic")
    fun getPicList(@Header("token") token:String): Observable<RecommendListBean>

    @Headers(
        "project_token:${APP.PROJECT_TOKEN}")
    @POST("home/attention/list")
    fun getFollowArt(@Body requestBody: RequestBody,@Header("token") token:String): Observable<RecommendListBean>

    @Headers(
        "project_token:${APP.PROJECT_TOKEN}")
    @POST("home/attention/recommend")
    fun getRecommendFollow(@Header("token") token:String): Observable<RecommendFollowBean>

    @Headers(
        "project_token:${APP.PROJECT_TOKEN}")
    @POST("jokes/comment/list")
    fun getCommentData(@Body requestBody: RequestBody,@Header("token") token:String):Observable<CommentData>


    @Headers(
        "project_token:${APP.PROJECT_TOKEN}")
    @POST("jokes/like")
    fun setIsLike(@Body requestBody: RequestBody,@Header("token") token:String):Observable<IsLikeData>

    @Headers(
        "project_token:${APP.PROJECT_TOKEN}")
    @POST("jokes/unlike")
    fun setIsNotLike(@Body requestBody: RequestBody,@Header("token") token:String):Observable<IsLikeData>

    @Headers(
        "project_token:${APP.PROJECT_TOKEN}")
    @POST("jokes/comment")
    fun postComment(@Body requestBody: RequestBody,@Header("token") token:String):Observable<PostCommentRespond>

    @Headers(
        "project_token:${APP.PROJECT_TOKEN}")
    @POST("user/attention")
    fun setUserFollow(@Body requestBody: RequestBody,@Header("token") token:String):Observable<AllData>

}