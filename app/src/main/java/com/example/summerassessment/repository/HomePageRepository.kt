package com.example.summerassessment.repository


import com.example.summerassessment.model.RecommendListBean
import com.example.summerassessment.services.ApiHomeService
import com.example.summerassessment.util.create

import rx.Observable

object HomePageRepository{


    private val apiHomeService=create<ApiHomeService>()

    fun getApiHomeService(): ApiHomeService {
        return apiHomeService
    }


    fun getPagingData(p:Int): Observable<RecommendListBean> {
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
            else->{
                apiHomeService.getPicList()
            }
        }

    }

    fun getRecommendFollowData()=apiHomeService.getRecommendFollow()


}