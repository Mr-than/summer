package com.example.summerassessment.repository

import com.example.summerassessment.model.CurrentUserData
import com.example.summerassessment.model.UserData
import com.example.summerassessment.services.ApiMyService
import com.example.summerassessment.util.create

import rx.Observable

object MyPageRepository {

    private val apiMyService= create<ApiMyService>()

    fun getData(): Observable<CurrentUserData> {
        return apiMyService.getUser()
    }

}