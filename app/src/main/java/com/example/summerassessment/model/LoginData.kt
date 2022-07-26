package com.example.summerassessment.model

data class LoginData(
    val code: Int,
    val `data`: DataL,
    val msg: String
)

data class DataL(
    val token: String,
    val type: String,
    val userInfo: UserInfo
)

data class UserInfo(
    val avatar: String,
    val birthday: String,
    val inviteCode: String,
    val invitedCode: String,
    val nickname: String,
    val sex: String,
    val signature: String,
    val userId: Int,
    val userPhone: String
)