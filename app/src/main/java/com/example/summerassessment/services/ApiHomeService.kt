package com.example.summerassessment.services

import com.example.summerassessment.base.APP
import com.example.summerassessment.model.RecommendFollowBean
import com.example.summerassessment.model.RecommendListBean

import retrofit2.http.*
import rx.Observable

interface ApiHomeService {

    @Headers("project_token:${APP.PROJECT_TOKEN}")
    @POST("home/recommend")
    fun getRecommendList(): Observable<RecommendListBean>


    @Headers("project_token:${APP.PROJECT_TOKEN}")
    @POST("home/latest")
    fun getNewList():Observable<RecommendListBean>

    @Headers("project_token:${APP.PROJECT_TOKEN}")
    @POST("home/text")
    fun getTextList():Observable<RecommendListBean>

    @Headers("project_token:${APP.PROJECT_TOKEN}")
    @POST("home/pic")
    fun getPicList():Observable<RecommendListBean>

    @Headers("project_token:${APP.PROJECT_TOKEN}")
    @POST("home/attention/list")
    fun getFollowArt(@Header("token")token:String,@Body page:Int):Observable<RecommendListBean>

    @Headers("project_token:${APP.PROJECT_TOKEN}")
    @POST("home/attention/recommend")
    fun getRecommendFollow():Observable<RecommendFollowBean>

}