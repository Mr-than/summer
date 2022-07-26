package com.example.summerassessment.repository

import com.example.summerassessment.model.RecommendListBean
import com.example.summerassessment.services.ApiSearchService
import com.example.summerassessment.util.create
import okhttp3.MultipartBody
import okhttp3.RequestBody
import rx.Observable

/**
 *   description:搜索页面的整个仓库
 *   @author:冉跃
 *   email:2058109198@qq.com
 */
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