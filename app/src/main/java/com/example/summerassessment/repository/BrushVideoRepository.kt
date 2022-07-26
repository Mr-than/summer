package com.example.summerassessment.repository

import com.example.summerassessment.services.ApiPlayService
import com.example.summerassessment.util.create


/**
 *   description:刷视频界面的仓库
 *   @author:冉跃
 *   email:2058109198@qq.com
 */
//其实这里可以不要仓库层，因为这里的这一层的功能单一且简单
object BrushVideoRepository {

    private val apiPlayService =create<ApiPlayService>()

    fun getVideoData()= apiPlayService.getVideoData()

}