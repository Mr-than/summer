package com.example.summerassessment.model

data class RecommendFollowBean(
    val code: Int,
    val `data`: List<DataX>,
    val msg: String
)

data class DataX(
    val avatar: String,
    val fansNum: String,
    val isAttention: Boolean,
    val jokesNum: String,
    val nickname: String,
    val userId: Int
)