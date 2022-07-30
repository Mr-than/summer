package com.example.summerassessment.repository

import com.example.summerassessment.base.APP
import com.example.summerassessment.model.CurrentUserData
import com.example.summerassessment.services.MyService
import com.example.summerassessment.util.create

import rx.Observable


/**
 *   description:“我的”页面fragment的整个仓库
 *   @author:冉跃
 *   email:2058109198@qq.com
 */
object MyPageRepository {

    private val myService= create<MyService>()

    fun getData(): Observable<CurrentUserData> {
        return myService.getUser(APP.token)
    }

}