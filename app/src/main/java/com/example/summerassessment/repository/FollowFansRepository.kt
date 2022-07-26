package com.example.summerassessment.repository

import com.example.summerassessment.base.APP
import com.example.summerassessment.model.AllData
import com.example.summerassessment.model.AttentionUserListData
import com.example.summerassessment.services.ApiFollowService
import com.example.summerassessment.util.create
import okhttp3.MultipartBody
import okhttp3.RequestBody
import rx.Observable


/**
 *   description:粉丝关注列表的仓库
 *   @author:冉跃
 *   email:2058109198@qq.com
 */
object FollowFansRepository {
    private val apiFollowService:ApiFollowService= create()


    fun getFollowData(id:String,page:String): Observable<AttentionUserListData> {

        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("page", page)
            .addFormDataPart("targetUserId",id)
            .build()

        return apiFollowService.userFollowList(requestBody, APP.token)
    }

    fun setUserFollow(state:String,id:String): Observable<AllData>{
        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("status", state)
            .addFormDataPart("userId",id)
            .build()

        return apiFollowService.setUserFollow(requestBody,APP.token)
    }

    fun getFanData(id:String,page:String): Observable<AttentionUserListData> {

        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("page", page)
            .addFormDataPart("targetUserId",id)
            .build()

        return apiFollowService.userFanList(requestBody,APP.token)
    }

}