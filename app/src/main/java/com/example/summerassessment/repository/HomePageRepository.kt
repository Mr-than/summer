package com.example.summerassessment.repository


import com.example.summerassessment.model.CommentData
import com.example.summerassessment.model.RecommendListBean
import com.example.summerassessment.services.ApiHomeService
import com.example.summerassessment.util.create
import okhttp3.MultipartBody
import okhttp3.RequestBody

import rx.Observable

object HomePageRepository{


    private val apiHomeService=create<ApiHomeService>()

    fun getApiHomeService(): ApiHomeService {
        return apiHomeService
    }


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
}