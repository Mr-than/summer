package com.example.summerassessment.model

data class UserData(
    val code: Int,
    val `data`: DataE,
    val msg: String
)

data class DataE(
    val attentionNum: String,
    val attentionState: Int,
    val avatar: String,
    val collectNum: String,
    val commentNum: String,
    val cover: String,
    val fansNum: String,
    val joinTime: String,
    val jokeLikeNum: String,
    val jokesNum: String,
    val likeNum: String,
    val nickname: String,
    val sigbature: String,
    val userId: Int
)