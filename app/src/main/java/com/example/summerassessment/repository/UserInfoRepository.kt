package com.example.summerassessment.repository

import com.example.summerassessment.base.APP
import com.example.summerassessment.model.*
import com.example.summerassessment.services.UserService
import com.example.summerassessment.util.create
import okhttp3.MultipartBody
import okhttp3.RequestBody
import rx.Observable


/**
 *   description:用户信息页面的整个仓库
 *   @author:冉跃
 *   email:2058109198@qq.com
 */
object UserInfoRepository {
    private val userService= create<UserService>()

    private fun getRequest(userId:Int,page:Int):RequestBody{
        return MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("targetUserId", "$userId")
            .addFormDataPart("page","$page")
            .build()
    }


    fun getVideoData(userId:Int,page:Int):Observable<UserVideoListData> {
        val requestBody= getRequest(userId,page)
        return userService.getVideoData(requestBody)
    }

    fun getTextImg(userId:Int,page:Int):Observable<RecommendListBean>{
        val requestBody= getRequest(userId,page)
        return userService.getTextImgData(requestBody)
    }

    fun getOwnVideo(userId:Int,page:Int):Observable<UserVideoListData>{
        val requestBody= getRequest(userId,page)
        return userService.getUserOwnVideoData(requestBody)
    }

    fun getOwnTextImg(userId:Int,page:Int):Observable<RecommendListBean>{
        val requestBody= getRequest(userId,page)
        return userService.getUserOwnTextImgData(requestBody)
    }

    fun getUserMessage(userId:Int): Observable<UserData>{

        val requestBody=MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("targetUserId", "$userId")
            .build()
        return userService.getUserMessage(requestBody)
    }

    fun getCurrentComment(page:Int):Observable<CurrentCommentData>{
        val requestBody=MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("page", "$page")
            .build()

        return userService.gerComment(requestBody, APP.token)
    }




}