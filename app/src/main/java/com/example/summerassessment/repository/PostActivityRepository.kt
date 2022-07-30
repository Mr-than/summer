package com.example.summerassessment.repository

import com.example.summerassessment.base.APP
import com.example.summerassessment.model.AllData
import com.example.summerassessment.model.PostTokenData
import com.example.summerassessment.services.PostService
import com.example.summerassessment.util.create
import okhttp3.MultipartBody
import okhttp3.RequestBody
import rx.Observable


/**
 *   description:发段子页面的整个仓库
 *   @author:冉跃
 *   email:2058109198@qq.com
 */
object PostActivityRepository {
    private val postService:PostService= create()

    fun getToken(name:String): Observable<PostTokenData> {
        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("filename", name)
            .addFormDataPart("type","0")
            .build()

        return postService.getToken(requestBody, APP.token)
    }

    fun postImg(content:String,url:String): Observable<AllData>{
        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("content", content)
            .addFormDataPart("image_size","1080x2400")
            .addFormDataPart("type","2")
            .addFormDataPart("image_urls",url)
            .build()
        return postService.postImg(requestBody,APP.token)
    }

    fun postText(content:String): Observable<AllData>{
        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("content", content)
            .addFormDataPart("type","1")
            .build()

        return postService.postImg(requestBody,APP.token)
    }

}