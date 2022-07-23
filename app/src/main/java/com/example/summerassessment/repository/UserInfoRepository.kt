package com.example.summerassessment.repository

import com.example.summerassessment.model.RecommendListBean
import com.example.summerassessment.model.UserData
import com.example.summerassessment.model.UserVideoListData
import com.example.summerassessment.services.ApiUserService
import com.example.summerassessment.util.create
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody
import rx.Observable

object UserInfoRepository {
    private val apiUserService= create<ApiUserService>()

    private fun getRequest(userId:Int,page:Int):RequestBody{
        return MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("targetUserId", "$userId")
            .addFormDataPart("page","$page")
            .build()
    }


    fun getVideoData(userId:Int,page:Int):Observable<UserVideoListData> {
        val requestBody= getRequest(userId,page)
        return apiUserService.getVideoData(requestBody)
    }

    fun getTextImg(userId:Int,page:Int):Observable<RecommendListBean>{
        val requestBody= getRequest(userId,page)
        return apiUserService.getTextImgData(requestBody)
    }

    fun getOwnVideo(userId:Int,page:Int):Observable<UserVideoListData>{
        val requestBody= getRequest(userId,page)
        return apiUserService.getUserOwnVideoData(requestBody)
    }

    fun getOwnTextImg(userId:Int,page:Int):Observable<RecommendListBean>{
        val requestBody= getRequest(userId,page)
        return apiUserService.getUserOwnTextImgData(requestBody)
    }

    fun getUserMessage(userId:Int): Observable<UserData>{

        val requestBody=MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("targetUserId", "$userId")
            .build()
        return apiUserService.getUserMessage(requestBody)
    }

}