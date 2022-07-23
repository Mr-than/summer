package com.example.summerassessment.repository

import com.example.summerassessment.model.RecommendListBean
import com.example.summerassessment.services.ApiSearchService
import com.example.summerassessment.util.create
import okhttp3.MultipartBody
import okhttp3.RequestBody
import rx.Observable

object SearchPageRepository {

    private val apiSearchService= create<ApiSearchService>()

    fun getSearchResultData(key:String,page:Int): Observable<RecommendListBean> {
        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("keyword",key)
            .addFormDataPart("page", "$page")
            .build()
        return apiSearchService.getVideoData(requestBody)
    }

}