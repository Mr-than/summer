package com.example.summerassessment.repository


import com.example.summerassessment.model.*
import com.example.summerassessment.services.ApiHomeService
import com.example.summerassessment.util.create
import okhttp3.MultipartBody
import okhttp3.RequestBody

import rx.Observable

object HomePageRepository{


    private val apiHomeService=create<ApiHomeService>()


    fun getPagingData(p:Int,page:Int): Observable<RecommendListBean> {
        return when(p){
            0->{
                apiHomeService.getRecommendList()
            }
            1->{
                apiHomeService.getNewList()
            }
            2->{
                apiHomeService.getTextList()
            }
            3->{
                apiHomeService.getPicList()
            }
            else->{

                val requestBody: RequestBody = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("page", "$page")
                    .build()

                apiHomeService.getFollowArt(requestBody)
            }
        }

    }

    fun getRecommendFollowData()=apiHomeService.getRecommendFollow()

    fun getCommentData(id:Int,a:Int):Observable<CommentData> {
        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("jokeId", "$id")
            .build()

        return apiHomeService.getCommentData(requestBody)
    }


    fun setLike(id:Int,state:Boolean):Observable<IsLikeData>{

        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("id", "$id")
            .addFormDataPart("status","$state")
            .build()

        return apiHomeService.setIsLike(requestBody)
    }

    fun setUnLike(id:Int,state:Boolean):Observable<IsLikeData>{

        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("id", "$id")
            .addFormDataPart("status","$state")
            .build()

        return apiHomeService.setIsNotLike(requestBody)
    }

    fun postComment(content:String,id:String):Observable<PostCommentRespond>{

        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("content", content)
            .addFormDataPart("jokeId",id)
            .build()

        return apiHomeService.postComment(requestBody)
    }


    fun setUserFollow(status:String,userId:String):Observable<AllData>{

        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("status", status)
            .addFormDataPart("userId",userId)
            .build()

        return apiHomeService.setUserFollow(requestBody)
    }

}