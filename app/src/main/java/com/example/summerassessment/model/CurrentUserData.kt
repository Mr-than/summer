package com.example.summerassessment.model

data class CurrentUserData(
    val code: Int,
    val `data`: DataC,
    val msg: String
)

data class DataC(
    val info: InfoC,
    val user: UserC
)

data class InfoC(
    val attentionNum: Int,
    val experienceNum: Int,
    val fansNum: Int,
    val likeNum: Int
)

data class UserC(
    val avatar: String,
    val birthday: String,
    val inviteCode: String,
    val invitedCode: Any,
    val nickname: String,
    val sex: String,
    val signature: String,
    val userId: Int,
    val userPhone: String
)