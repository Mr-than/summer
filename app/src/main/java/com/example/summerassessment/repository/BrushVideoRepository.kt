package com.example.summerassessment.repository

import com.example.summerassessment.services.ApiPlayService
import com.example.summerassessment.util.create

object BrushVideoRepository {

    private val apiPlayService =create<ApiPlayService>()

    fun getVideoData()= apiPlayService.getVideoData()

}