package com.example.summerassessment.model

data class CurrentCommentData(
    val code: Int,
    val `data`: List<DataM>,
    val msg: String
)

data class DataM(
    val commentId: Int,
    val content: String,
    val extraContent: String,
    val msgId: Int,
    val msgItemType: Int,
    val msgItemTypeDesc: String,
    val msgMainType: Int,
    val msgMainTypeDesc: String,
    val msgStatus: Int,
    val msgTime: String,
    val ownerUserId: Int,
    val targetId: Int,
    val targetNickname: String,
    val targetUserAvatar: String,
    val targetUserId: Int
)