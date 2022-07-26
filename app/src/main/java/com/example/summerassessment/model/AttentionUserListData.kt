package com.example.summerassessment.model

data class AttentionUserListData(
    val code: Int,
    val `data`: List<DataQ>,
    val msg: String
)

data class DataQ(
    val attention: Int,
    val avatar: String,
    val nickname: String,
    val signature: String,
    val userId: Int
)