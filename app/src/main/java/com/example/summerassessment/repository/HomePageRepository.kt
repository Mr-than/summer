package com.example.summerassessment.repository


import com.example.summerassessment.base.APP
import com.example.summerassessment.model.*
import com.example.summerassessment.services.HomeService
import com.example.summerassessment.util.create
import okhttp3.MultipartBody
import okhttp3.RequestBody

import rx.Observable


/**
 *   description:主页面fragment的整个仓库
 *   @author:冉跃
 *   email:2058109198@qq.com
 */
object HomePageRepository{


    private val homeService=create<HomeService>()


    fun getPagingData(p:Int,page:Int): Observable<RecommendListBean> {
        return when(p){
            0->{
                val a=APP.token
                homeService.getRecommendList(APP.token)
            }
            1->{
                homeService.getNewList(APP.token)
            }
            2->{
                homeService.getTextList(APP.token)
            }
            3->{
                homeService.getPicList(APP.token)
            }
            else->{

                val requestBody: RequestBody = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("page", "$page")
                    .build()

                homeService.getFollowArt(requestBody,APP.token)
            }
        }

    }

    fun getRecommendFollowData()=homeService.getRecommendFollow(APP.token)

    fun getCommentData(id:Int,a:Int):Observable<CommentData> {
        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("jokeId", "$id")
            .build()

        return homeService.getCommentData(requestBody,APP.token)
    }


    fun setLike(id:Int,state:Boolean):Observable<IsLikeData>{

        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("id", "$id")
            .addFormDataPart("status","$state")
            .build()

        return homeService.setIsLike(requestBody,APP.token)
    }

    fun setUnLike(id:Int,state:Boolean):Observable<IsLikeData>{

        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("id", "$id")
            .addFormDataPart("status","$state")
            .build()

        return homeService.setIsNotLike(requestBody,APP.token)
    }

    fun postComment(content:String,id:String):Observable<PostCommentRespond>{

        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("content", content)
            .addFormDataPart("jokeId",id)
            .build()

        return homeService.postComment(requestBody,APP.token)
    }


    fun setUserFollow(status:String,userId:String):Observable<AllData>{

        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("status", status)
            .addFormDataPart("userId",userId)
            .build()

        return homeService.setUserFollow(requestBody,APP.token)
    }

}