package com.example.summerassessment.repository

import com.example.summerassessment.base.APP
import com.example.summerassessment.model.CurrentUserData
import com.example.summerassessment.model.UserData
import com.example.summerassessment.services.ApiMyService
import com.example.summerassessment.util.create
import retrofit2.http.Header

import rx.Observable


/**
 *   description:“我的”页面fragment的整个仓库
 *   @author:冉跃
 *   email:2058109198@qq.com
 */
object MyPageRepository {

    private val apiMyService= create<ApiMyService>()

    fun getData(): Observable<CurrentUserData> {
        return apiMyService.getUser(APP.token)
    }

}