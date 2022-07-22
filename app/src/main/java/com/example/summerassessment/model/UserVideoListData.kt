package com.example.summerassessment.model

data class UserVideoListData(
    val code: Int,
    val `data`: List<DataU>,
    val msg: String
)

data class DataU(
    val cover: String,
    val jokeId: Int,
    val likeNum: String
)